package com.pragya.expensetracker.repository;

import com.pragya.expensetracker.entity.PlannedExpense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlannedExpenseRepository
        extends JpaRepository<PlannedExpense, Long> {

    List<PlannedExpense> findByUsernameAndYearAndMonth(
            String username, int year, int month
    );
}
