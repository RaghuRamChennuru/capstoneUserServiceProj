package com.ram.capstoneuserserviceproj.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@MappedSuperclass
@Getter
@Setter
public class BaseClass
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean deleted;
    private Date CreatedOn;

}
