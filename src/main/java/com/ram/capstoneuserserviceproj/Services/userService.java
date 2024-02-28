package com.ram.capstoneuserserviceproj.Services;

import com.ram.capstoneuserserviceproj.Exceptions.TokenNotFoundException;
import com.ram.capstoneuserserviceproj.Models.Token;
import com.ram.capstoneuserserviceproj.Models.User;
import com.ram.capstoneuserserviceproj.Repositories.tokenEntityRepositry;
import com.ram.capstoneuserserviceproj.Repositories.userEntityRepositry;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@Service
public class userService implements userServiceInterface
{
    private userEntityRepositry userRepositry;
    private BCryptPasswordEncoder passwordEncoder;
    private tokenEntityRepositry tokenRepositry;

    @Autowired
    userService(userEntityRepositry userEntityRespositry,
                BCryptPasswordEncoder passwordEncoder,
                tokenEntityRepositry tokenEntityRepositry)
    {
        this.userRepositry = userEntityRespositry;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepositry = tokenEntityRepositry;
    }

    @Override
    public User SignUp(String Name,String EmailId, String Password)
    {

        User newUser = new User();

        newUser.setName(Name);
        newUser.setEmailId(EmailId);
        newUser.setHashedPassword(this.passwordEncoder.encode(Password));

        User respUser = this.userRepositry.save(newUser);

        return respUser;
    }

    @Override
    public Token LoginUser(String emailID,String Password)
    {
        Optional<User> userResp = this.userRepositry.findUserByEmailId(emailID);

        if(userResp.isEmpty())
        {
            throw new RuntimeException("User Not Available");
        }

        boolean matches = this.passwordEncoder.matches(Password, userResp.get().getHashedPassword());

        if(!matches)
        {
            throw new RuntimeException("Incorrect Password");
        }

        String Token = generateToken();

        Token newToken  = new Token();

        newToken.setUser(userResp.get());
        newToken.setToken(Token);


        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysLater = today.plus(30, ChronoUnit.DAYS);

        // Convert LocalDate to Date
        Date expiryDate = Date.from(thirtyDaysLater.atStartOfDay(ZoneId.systemDefault()).toInstant());

        newToken.setExpiryDate(expiryDate);

        Token respToken  = this.tokenRepositry.save(newToken);

        return respToken;
    }

    private String generateToken()
    {
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        return generatedString;
    }



    @Override
    public boolean LogoutUser(String token)
    {
        Optional<Token> respToken =  this.tokenRepositry.findByToken(token);

        if(respToken.isEmpty())
        {
            throw new RuntimeException("Not a Valid Token");
        }

        Token tkn = respToken.get();

        tkn.setDeleted(true);

       tkn = this.tokenRepositry.save(tkn);



        return true;
    }

    @Override
    public User ValidateUserToken(String token)throws TokenNotFoundException
    {
        Optional<Token> respToken = this.tokenRepositry.findByToken(token);

        if(respToken.isEmpty())
        {
            throw new TokenNotFoundException("Token Not Found");
        }

        User respUser = respToken.get().getUser();

        return respUser;
    }


}
