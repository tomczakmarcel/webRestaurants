package com.example.web_restauracje.controller;

import com.example.web_restauracje.models.Database;
import com.example.web_restauracje.models.Reservation;
import com.example.web_restauracje.models.User;
import com.example.web_restauracje.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public String getAllUsers(Model model, User user) throws ExecutionException, InterruptedException {
        model.addAttribute("users",Database.loadUserList());
        return "userList";
    }
}