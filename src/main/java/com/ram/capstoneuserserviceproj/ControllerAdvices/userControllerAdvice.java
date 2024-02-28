package com.ram.capstoneuserserviceproj.ControllerAdvices;

import com.ram.capstoneuserserviceproj.DTO.ExceptionDTO;
import com.ram.capstoneuserserviceproj.Exceptions.TokenNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class userControllerAdvice
{

    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<ExceptionDTO> tokenNotFound(TokenNotFoundException exception)
    {
        ExceptionDTO dto = new ExceptionDTO();
        dto.setMessage(exception.getMessage());

        return new ResponseEntity<>(dto,HttpStatus.NOT_FOUND);

    }
}
