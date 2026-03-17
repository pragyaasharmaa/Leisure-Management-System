package com.pragya.expensetracker.service;

import com.pragya.expensetracker.entity.MonthlyIncome;
import com.pragya.expensetracker.repository.MonthlyIncomeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MonthlyIncomeService {

    private final MonthlyIncomeRepository repo;

    public MonthlyIncomeService(MonthlyIncomeRepository repo) {
        this.repo = repo;
    }

    public double getIncome(String username, int year, int month) {
        return repo.findByUsernameAndYearAndMonth(username, year, month)
                .map(MonthlyIncome::getAmount)
                .orElse(0.0);
    }

    @Transactional
    public double saveIncome(String username, int year, int month, double amount) {

        MonthlyIncome income = repo.findByUsernameAndYearAndMonth(username, year, month)
                .orElse(null);

        // FORCE UPDATE instead of new row
        if (income == null) {
            income = new MonthlyIncome();
            income.setUsername(username);
            income.setYear(year);
            income.setMonth(month);
        }

        income.setAmount(amount);

        repo.save(income);
        return income.getAmount();
    }
}
