package com.ram.capstoneuserserviceproj.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class User extends BaseClass
{
    private String name;
    private String emailId;
    private String hashedPassword;
    private boolean isVerified;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;


}
