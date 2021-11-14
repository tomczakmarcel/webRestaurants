package com.example.web_restauracje.controller;

import com.example.web_restauracje.firebase.FirebaseInit;
import com.example.web_restauracje.models.Database;
import com.example.web_restauracje.models.Restaurant;
import com.example.web_restauracje.service.RestaurantService;
import com.google.firebase.database.*;
import net.thegreshams.firebase4j.error.FirebaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Controller
public class HomeController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/")
    public String HomeController() throws ExecutionException, InterruptedException, FirebaseException, IOException {
        FirebaseInit.init();
        return "index";
    }
}
