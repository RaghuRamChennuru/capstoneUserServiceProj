package com.ram.capstoneuserserviceproj.Models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Role extends BaseClass
{
    private String name;
}
