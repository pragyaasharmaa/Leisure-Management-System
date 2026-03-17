package com.pragya.expensetracker.dto;

import java.util.Map;

public class ExpenseSummaryResponse {

    private double totalAmount;
    private long totalExpenses;
    private Map<String, Double> categoryWiseAmount;

    public ExpenseSummaryResponse() {}

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public long getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(long totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public Map<String, Double> getCategoryWiseAmount() {
        return categoryWiseAmount;
    }

    public void setCategoryWiseAmount(Map<String, Double> categoryWiseAmount) {
        this.categoryWiseAmount = categoryWiseAmount;
    }
}

