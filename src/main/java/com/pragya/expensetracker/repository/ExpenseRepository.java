package com.pragya.expensetracker.repository;

import com.pragya.expensetracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

//optional filtering
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Page<Expense> findByCategory(String category, Pageable pageable);
    Page<Expense> findByUsername(String username, Pageable pageable);
    Page<Expense> findByCategoryAndUsername(String category, String username, Pageable pageable);
}
