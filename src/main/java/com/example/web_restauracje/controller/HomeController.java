package com.example.web_restauracje.controller;

import com.example.web_restauracje.firebase.FirebaseInit;
import com.example.web_restauracje.service.RestaurantService;
import com.example.web_restauracje.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Controller
public class HomeController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/")
    public String HomeController() throws ExecutionException, InterruptedException {
        FirebaseInit.init();
        return "index";
    }
}
