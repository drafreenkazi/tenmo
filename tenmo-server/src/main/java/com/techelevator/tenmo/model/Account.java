package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Account {

    private int accountId;
    private int userId;
    private Double balance;

    public Account (int accountId, int userId, Double balance){
        this.accountId = accountId;
        this.userId = userId;
        this.balance = balance;
    }

    public Account() {
    }


    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }



}
