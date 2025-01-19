package com.example.autobase.controller;
import com.example.autobase.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class RequestController {
    @Autowired
    private RequestRepository requestRepository;
    @GetMapping("/requests")
    public String listRequests(Model model) {
        model.addAttribute("requests", requestRepository.findAll());
        return "requests";
    }
}