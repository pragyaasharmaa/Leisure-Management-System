package com.pragya.expensetracker.repository;

import com.pragya.expensetracker.entity.MonthlyIncome;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MonthlyIncomeRepository
        extends JpaRepository<MonthlyIncome, Long> {

    Optional<MonthlyIncome> findByUsernameAndYearAndMonth(
            String username,
            int year,
            int month
    );
}
