package com.example.web_restauracje.controller;

import com.example.web_restauracje.models.Database;
import com.example.web_restauracje.models.Meal;
import com.example.web_restauracje.models.OpeningHour;
import com.example.web_restauracje.service.RestaurantService;
import com.google.firebase.FirebaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/")
    public String getAllRestaurants(Model model) {
        model.addAttribute("restaurants", Database.getRestaurantList());
        return "restaurants";
    }

    @GetMapping("/{restaurantName}")
    public String getRestaurantDetails(Model model, @PathVariable String restaurantName) {
        String date[] = {"Monday-Thursday", "Friday", "Saturday", "Sunday"};
        try {
            model.addAttribute("restaurantInfo", Database.getRestaurant(restaurantName));
            for (String dates : date)
                model.addAttribute("openingHours", Database.getOpeningHour(restaurantName, dates));

            model.addAttribute("mealList", Database.getMealListFromRestaurant(restaurantName));
        } catch (Exception noRestaurantOfThisName) {
            return "noRestaurantAvailable";
        }

        return "restaurant";
    }

    @GetMapping("/{restaurantName}/meallist")
    public String getRestaurantMeals(Model model, @PathVariable String restaurantName) throws ExecutionException, InterruptedException, FirebaseException
    {
        try
        {
            model.addAttribute("restaurantInfo",Database.getRestaurant(restaurantName));
            model.addAttribute("mealList", Database.getMealListFromRestaurant(restaurantName));
        }
        catch (Exception noRestaurantOfThisName)
        {
            return "noRestaurantAvailable";
        }

        return "mealList";
    }

    @GetMapping("/{restaurantName}/meallist/{mealName}")
    public String getRestaurantMeals(Model model, @PathVariable String restaurantName, @PathVariable String mealName) throws ExecutionException, InterruptedException, FirebaseException, IOException {
        model.addAttribute("restaurantInfo",Database.getRestaurant(restaurantName));
        model.addAttribute("mealList", Database.getMealListFromRestaurant(restaurantName));
        model.addAttribute("meal", Database.getMeal(restaurantName, mealName));

        return "editMeal";
    }

    @PostMapping("/{restaurantName}/meallist/{mealName}")
    public String editRestaurantMeals(Model model, @PathVariable String restaurantName, @PathVariable String mealName, Meal meal) throws ExecutionException, InterruptedException, FirebaseException
    {
        try
        {
            Database.editMeal(restaurantName, mealName, meal.getCategory(), meal.getName(), meal.getPrice(), meal.getPhotoUrl());
        } catch (IOException e) {
            return "noRestaurantAvailable";
        }
        model.addAttribute("restaurantInfo",Database.getRestaurant(restaurantName));
        model.addAttribute("mealList", Database.getMealListFromRestaurant(restaurantName));

        return "/mealList";
    }

    @GetMapping(value = "/{restaurantName}/meallist/{mealName}/delete")
    public String deleteMeal(Model model, @PathVariable String restaurantName, @PathVariable String mealName, Meal meal) throws ExecutionException, InterruptedException, FirebaseException
    {
        try
        {
            Database.removeMeal(restaurantName, mealName);
        } catch (IOException e) {
            return "noRestaurantAvailable";
        }
        model.addAttribute("restaurantInfo",Database.getRestaurant(restaurantName));
        model.addAttribute("mealList", Database.getMealListFromRestaurant(restaurantName));
        return "mealList";
    }

    @GetMapping(value = "/{restaurantName}/meallist/add")
    public String addMeal(Model model, @PathVariable String restaurantName, Meal meal) throws ExecutionException, InterruptedException, FirebaseException
    {
        model.addAttribute("restaurantInfo",Database.getRestaurant(restaurantName));
        return "addMeal";
    }

    @PostMapping(value = "/{restaurantName}/meallist/add")
    public String saveMeal(Model model, @PathVariable String restaurantName, Meal meal) throws ExecutionException, InterruptedException, FirebaseException
    {
        try
        {
            Database.addMeal(restaurantName, meal.getCategory(), meal.getName(), meal.getPrice(), meal.getPhotoUrl());
        } catch (IOException e) {
            return "noRestaurantAvailable";
        }
        model.addAttribute("restaurantInfo",Database.getRestaurant(restaurantName));
        model.addAttribute("mealList", Database.getMealListFromRestaurant(restaurantName));
        return "/mealList";
    }

    @GetMapping("/{restaurantName}/openinghours")
        public String getRestaurantOpeningHours(Model model, @PathVariable String restaurantName) throws ExecutionException, InterruptedException, FirebaseException {
            try
            {
                model.addAttribute("restaurantInfo",Database.getRestaurant(restaurantName));
                var test = Database.getOpeningHours(restaurantName);
                model.addAttribute("openingHours", test);
            }
            catch (Exception noRestaurantOfThisName)
            {
                return "noRestaurantAvailable";
            }

            return "openingHours";
        }

    @GetMapping("/{restaurantName}/openinghours/{when}")
    public String editRestaurantOpeningHours(Model model, @PathVariable String restaurantName, @PathVariable String when) throws ExecutionException, InterruptedException, FirebaseException, IOException {
        model.addAttribute("restaurantInfo",Database.getRestaurant(restaurantName));
        model.addAttribute("openingHour", Database.getOpeningHour(restaurantName, when));
        return "editOpeningHour";
    }

    @PostMapping("/{restaurantName}/openinghours/{when}")
    public String updateRestaurantOpeningHours(Model model, @PathVariable String restaurantName, @PathVariable String when, OpeningHour openingHour) throws ExecutionException, InterruptedException, FirebaseException, IOException {
        try
        {
            Database.editHours(restaurantName, openingHour.getClose(), openingHour.getOpen(), when);
        }
        catch (Exception noRestaurantOfThisName)
        {
            return "noRestaurantAvailable";
        }

        model.addAttribute("restaurantInfo",Database.getRestaurant(restaurantName));
        model.addAttribute("openingHours", Database.getOpeningHours(restaurantName)); //MARTOM - strona restauracji nei moze sie wyjebywac jak braknie opening hours!!!
        return "openingHours";
    }
    }