package com.ram.capstoneuserserviceproj.Models;

import jakarta.persistence.*;

import java.util.Date;


@MappedSuperclass
public class BaseClass
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean deleted;
    private Date CreatedOn;

}
