package com.tmav2.controller;

import com.tmav2.model.User;
import com.tmav2.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserRepo userRepo;

    public ResponseEntity<User> createUser(@RequestBody User user){
        User userCreated = userRepo.save(user);
        return new ResponseEntity<>(userCreated, HttpStatus.OK);
    }

}
