package com.ram.capstoneuserserviceproj.DTO;

import com.ram.capstoneuserserviceproj.Models.Role;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class userResponseDTO
{
    private String name;
    private String emailId;
    private List<Role> roles;
}
