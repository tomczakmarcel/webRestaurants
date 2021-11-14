package com.example.web_restauracje.controller;

import com.example.web_restauracje.models.Database;
import com.example.web_restauracje.models.Restaurant;
import com.example.web_restauracje.service.RestaurantService;
import net.thegreshams.firebase4j.error.FirebaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/api")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public String getAllRestaurants(Model model) throws ExecutionException, InterruptedException, FirebaseException {
        model.addAttribute("restaurants", Database.getRestaurantList());
        return "restaurants";
    }
}
