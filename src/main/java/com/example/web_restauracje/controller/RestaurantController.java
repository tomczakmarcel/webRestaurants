package com.example.web_restauracje.controller;

import com.example.web_restauracje.models.Database;
import com.example.web_restauracje.models.Product;
import com.example.web_restauracje.models.Restaurant;
import com.example.web_restauracje.service.ProductService;
import com.example.web_restauracje.service.RestaurantService;
import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.service.Firebase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants/{name}")
    public Restaurant getRestaurant(@PathVariable String name) throws ExecutionException, InterruptedException, FirebaseException {
        restaurantService.getRestaurantDatabaseDetailsByName();
        var test = Database.getRestaurant(name);
        return test;
    }
}
