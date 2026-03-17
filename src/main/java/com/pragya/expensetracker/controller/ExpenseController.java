package com.pragya.expensetracker.controller;

import com.pragya.expensetracker.entity.Expense;
import com.pragya.expensetracker.service.ExpenseService;
import com.pragya.expensetracker.dto.ExpensePageResponse;

import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.pragya.expensetracker.dto.ExpenseSummaryResponse; // summary api

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    // ADD expense
    @PostMapping
    public Expense addExpense(@RequestBody Expense expense) {
        return expenseService.addExpense(expense);
    }

    // GET expense by id
    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable Long id) {
        return expenseService.getExpenseById(id);
    }

    // DELETE expense
    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
    }

    // UPDATE expense
    @PutMapping("/{id}")
    public Expense updateExpense(
            @PathVariable Long id,
            @RequestBody Expense expense) {

        return expenseService.updateExpense(id, expense);
    }

    // PAGINATED and filtering response (custom DTO)
    @GetMapping
    public ExpensePageResponse getExpenses(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String username, // ✅ ADDED
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return expenseService.getExpensesWithPagination(category, username, pageable);
    }
    @GetMapping("/summary")
    public ExpenseSummaryResponse getExpenseSummary() {
        return expenseService.getExpenseSummary();
    }

}


