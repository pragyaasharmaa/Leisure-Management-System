package com.pragya.expensetracker.service;

import com.pragya.expensetracker.entity.Expense;
import com.pragya.expensetracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pragya.expensetracker.dto.ExpensePageResponse;

import com.pragya.expensetracker.dto.ExpenseSummaryResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }
    // Delete expense by id
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
    // Update expense by id
    public Expense updateExpense(Long id, Expense updatedExpense) {

        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Expense not found")
                );


        existingExpense.setTitle(updatedExpense.getTitle());
        existingExpense.setAmount(updatedExpense.getAmount());
        existingExpense.setCategory(updatedExpense.getCategory());
        existingExpense.setDate(updatedExpense.getDate());

        return expenseRepository.save(existingExpense);
    }
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));
    }

    public Page<Expense> getAllExpenses(Pageable pageable) {
        return expenseRepository.findAll(pageable);
    }
    public ExpensePageResponse getExpensesWithPagination(String category, String username, Pageable pageable) {

        Page<Expense> page;

        if (category == null || category.isBlank()) {
            // Filter by username only
            page = expenseRepository.findByUsername(username, pageable);
        } else {
            // Filter by both category and username
            page = expenseRepository.findByCategoryAndUsername(category, username, pageable);
        }

        ExpensePageResponse response = new ExpensePageResponse();
        response.setExpenses(page.getContent());
        response.setPage(page.getNumber());
        response.setSize(page.getSize());
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());

        return response;
    }
    public ExpenseSummaryResponse getExpenseSummary() {
        List<Expense> allExpenses = expenseRepository.findAll();

        double totalAmount = allExpenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();

        long totalExpenses = allExpenses.size();

        Map<String, Double> categoryWiseAmount = allExpenses.stream()
                .collect(Collectors.groupingBy(
                        Expense::getCategory,
                        Collectors.summingDouble(Expense::getAmount)
                ));

        ExpenseSummaryResponse response = new ExpenseSummaryResponse();
        response.setTotalAmount(totalAmount);
        response.setTotalExpenses(totalExpenses);
        response.setCategoryWiseAmount(categoryWiseAmount);
        return response;
    }
}

