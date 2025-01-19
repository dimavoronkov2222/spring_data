package com.example.autobase.controller;
import com.example.autobase.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class DriverController {
    @Autowired
    private DriverRepository driverRepository;
    @GetMapping("/drivers")
    public String listDrivers(Model model) {
        model.addAttribute("drivers", driverRepository.findAll());
        return "drivers";
    }
}