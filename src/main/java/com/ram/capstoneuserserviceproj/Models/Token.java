package com.ram.capstoneuserserviceproj.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Entity
@Getter
@Setter
public class Token extends BaseClass
{
    private String token;
    private Date expiryDate;

   @ManyToOne
    private User user;

}
