package com.example.web_restauracje.controller;

import com.example.web_restauracje.models.User;
import com.example.web_restauracje.models.User;
import com.example.web_restauracje.service.UserService;
import com.example.web_restauracje.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public String saveUser(@RequestBody User user) throws ExecutionException, InterruptedException {

        return userService.saveUser(user);
    }

    @GetMapping("/users/{name}")
    public User getUser(@PathVariable String name) throws ExecutionException, InterruptedException {

        return userService.getUserDetailsByName(name);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() throws ExecutionException, InterruptedException {

        return userService.getUserDetails();
    }


    @PutMapping("/users")
    public String update(@RequestBody User user) throws ExecutionException, InterruptedException {

        return userService.updateUser(user);
    }


    @DeleteMapping("/users/{name}")
    public String deleteUser(@PathVariable String name) throws ExecutionException, InterruptedException {

        return userService.deleteUser(name);
    }
}