package com.pragya.expensetracker.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "monthly_income",
        uniqueConstraints = @UniqueConstraint(columnNames = {"username", "year", "month"})
)
public class MonthlyIncome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private int year;
    private int month;

    @Column(name = "income_amount")
    private double amount;

    public MonthlyIncome() {}

    public Long getId() { return id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public int getMonth() { return month; }
    public void setMonth(int month) { this.month = month; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
