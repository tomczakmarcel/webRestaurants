package com.example.web_restauracje.controller;

import com.example.web_restauracje.models.Database;
import com.example.web_restauracje.models.Reservation;
import com.example.web_restauracje.models.Restaurant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    Database database = Database.getInstance();

    @GetMapping("/")
    public String getAllReservation(Model model) throws ExecutionException, InterruptedException {
        Database.loadReservationList();
        model.addAttribute("reservationList", Database.getAllReservationList());

        return "reservation";
    }
    @GetMapping("/updateReservation/{id}")
    public String updateReservation(Model model, @PathVariable String id) {
        model.addAttribute("reservation", Database.getReservationById(id));
        return "updateReservation";
    }

    @PostMapping("/updateReservation/{id}")
    public String updateReservationSubmit(@ModelAttribute Reservation reservation, Model model, RedirectAttributes redirAttrs) {
        model.addAttribute("reservation", reservation);
        redirAttrs.addFlashAttribute("success", "The reservation has been updated");
        Database.UpdateReservation(reservation);
        return "redirect:/reservation/";
    }

    @GetMapping("/deleteReservation/{id}")
    public String deleteReservation(@PathVariable String id, RedirectAttributes redirAttrs) {
        Database.DeleteReservation(Database.getReservationById(id));
        redirAttrs.addFlashAttribute("success", "The reservation has been deleted");
        return "redirect:/reservation/";
    }
}
