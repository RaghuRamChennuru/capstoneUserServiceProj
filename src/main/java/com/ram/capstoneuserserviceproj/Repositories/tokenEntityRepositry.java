package com.ram.capstoneuserserviceproj.Repositories;

import com.ram.capstoneuserserviceproj.Models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface tokenEntityRepositry extends JpaRepository<Token,Long>
{
    Token save(Token token);

    Optional<Token> findByToken(String Token);


}
