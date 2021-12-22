package com.example.expensestracker;

/**
 * Created by ul on 2/1/2019.
 */

public class ExpenseManager {
    int id;
    int amount;
    String description;
    String category;
    public ExpenseManager(int id, int amount, String description, String category){
        this.id=id;
        this.amount=amount;
        this.description=description;
        this.category=category;
    }
    public ExpenseManager(int amount, String description, String category){
        this.amount=amount;
        this.description=description;
        this.category=category;
    }
    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }
}
