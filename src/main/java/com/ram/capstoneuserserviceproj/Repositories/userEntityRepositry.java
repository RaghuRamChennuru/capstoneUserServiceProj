package com.ram.capstoneuserserviceproj.Repositories;

import com.ram.capstoneuserserviceproj.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userEntityRepositry extends JpaRepository<User, Long>
{
    User save(User user);

    Optional<User> findUserByEmailId(String EmailId);

}
