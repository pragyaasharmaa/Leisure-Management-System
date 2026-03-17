package com.pragya.expensetracker.controller;

import com.pragya.expensetracker.entity.PlannedExpense;
import com.pragya.expensetracker.service.PlannedExpenseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kpi/todos")
@CrossOrigin(origins = "*")
public class PlannedExpenseController {

    private final PlannedExpenseService plannedExpenseService;

    public PlannedExpenseController(PlannedExpenseService plannedExpenseService) {
        this.plannedExpenseService = plannedExpenseService;
    }

    @GetMapping
    public List<PlannedExpense> getTodos(
            @RequestParam int year,
            @RequestParam int month,
            @RequestParam String username // ✅ ADDED
    ) {
        return plannedExpenseService.getMonthlyTodos(username, year, month);
    }

    @PostMapping
    public PlannedExpense addTodo(@RequestBody PlannedExpense expense) {
        // Username comes from request body now
        return plannedExpenseService.addTodo(expense);
    }

    @PutMapping("/{id}/toggle")
    public PlannedExpense toggleTodo(@PathVariable Long id) {
        return plannedExpenseService.toggleTodo(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        plannedExpenseService.deleteTodo(id);
    }
}