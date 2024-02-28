package com.ram.capstoneuserserviceproj.Controllers;

import com.ram.capstoneuserserviceproj.DTO.loginRequestDTO;
import com.ram.capstoneuserserviceproj.DTO.logoutRequestDTO;
import com.ram.capstoneuserserviceproj.DTO.signUpRequestDTO;
import com.ram.capstoneuserserviceproj.DTO.userResponseDTO;
import com.ram.capstoneuserserviceproj.Exceptions.TokenNotFoundException;
import com.ram.capstoneuserserviceproj.Models.Token;
import com.ram.capstoneuserserviceproj.Models.User;
import com.ram.capstoneuserserviceproj.Services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class userController
{

    private userService userService;

    @Autowired
    userController(userService userService)
    {
        this.userService = userService;
    }


    @PostMapping("/signup")
    public User userSignIn(@RequestBody signUpRequestDTO RequestDTO)
    {
        User newUser = this.userService.SignUp(RequestDTO.getName(), RequestDTO.getEmailId(), RequestDTO.getPassword());

        return newUser;
    }


    @PostMapping("/login")
    public Token userLogin(@RequestBody loginRequestDTO RequestDTO)
    {
        Token newToken = this.userService.LoginUser(RequestDTO.getEmailId(),RequestDTO.getPassword());

        return newToken;
    }

    @PostMapping("/logout")
    public boolean userLogout(@RequestBody logoutRequestDTO RequestDtO)
    {
        return this.userService.LogoutUser(RequestDtO.getToken());
    }

    @GetMapping("/validateToken/{id}")
    public userResponseDTO validateToken(@PathVariable("id") String id) throws TokenNotFoundException {
        User respuser  = this.userService.ValidateUserToken(id);

        if(respuser == null)
        {
            return null;
        }

        userResponseDTO dto = new userResponseDTO();

        dto.setName(respuser.getName());
        dto.setEmailId(respuser.getEmailId());
        dto.setRoles(respuser.getRoles());

        return dto;
    }

}
