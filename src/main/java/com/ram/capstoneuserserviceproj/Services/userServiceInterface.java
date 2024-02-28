package com.ram.capstoneuserserviceproj.Services;

import com.ram.capstoneuserserviceproj.Exceptions.TokenNotFoundException;
import com.ram.capstoneuserserviceproj.Models.Token;
import com.ram.capstoneuserserviceproj.Models.User;

import java.util.Optional;

public interface userServiceInterface
{
    User SignUp(String Name,String EmailId, String Password);

    Token LoginUser(String emailID,String Password);

    boolean LogoutUser(String token);

    User ValidateUserToken(String token) throws TokenNotFoundException;
}
