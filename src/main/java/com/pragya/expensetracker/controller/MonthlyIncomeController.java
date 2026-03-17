package com.pragya.expensetracker.controller;

import com.pragya.expensetracker.service.MonthlyIncomeService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/kpi/income")
@CrossOrigin(origins = "*")
public class MonthlyIncomeController {

    private final MonthlyIncomeService service;

    public MonthlyIncomeController(MonthlyIncomeService service) {
        this.service = service;
    }

    @GetMapping
    public Map<String, Double> getIncome(
            @RequestParam int year,
            @RequestParam int month,
            @RequestParam String username // ✅ ADDED
    ) {
        double amount = service.getIncome(username, year, month);
        return Map.of("amount", amount);
    }

    @PostMapping
    public Map<String, Double> saveIncome(
            @RequestBody Map<String, Object> body
    ) {
        int year = (int) body.get("year");
        int month = (int) body.get("month");
        double amount = ((Number) body.get("amount")).doubleValue();
        String username = (String) body.get("username"); // ✅ ADDED

        double saved = service.saveIncome(username, year, month, amount);
        return Map.of("amount", saved);
    }
}