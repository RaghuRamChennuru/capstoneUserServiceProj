package com.ram.capstoneuserserviceproj.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.Date;


@Entity
public class Token extends BaseClass
{
    private String token;
    private Date expiryDate;

   @ManyToOne
    private User user;

}
