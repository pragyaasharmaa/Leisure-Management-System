package com.pragya.expensetracker.service;

import com.pragya.expensetracker.entity.PlannedExpense;
import com.pragya.expensetracker.repository.PlannedExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlannedExpenseService {

    private final PlannedExpenseRepository plannedExpenseRepository;

    public PlannedExpenseService(PlannedExpenseRepository plannedExpenseRepository) {
        this.plannedExpenseRepository = plannedExpenseRepository;
    }

    public List<PlannedExpense> getMonthlyTodos(
            String username,
            int year,
            int month
    ) {
        return plannedExpenseRepository
                .findByUsernameAndYearAndMonth(username, year, month);
    }

    public PlannedExpense addTodo(PlannedExpense expense) {
        return plannedExpenseRepository.save(expense);
    }

    public PlannedExpense toggleTodo(Long id) {
        PlannedExpense expense = plannedExpenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Planned expense not found"));

        expense.setDone(!expense.isDone());
        return plannedExpenseRepository.save(expense);
    }

    public void deleteTodo(Long id) {
        plannedExpenseRepository.deleteById(id);
    }
}
