package com.mybanksystem.models.contracts;

import com.mybanksystem.models.enums.AccountType;
import com.mybanksystem.models.enums.Currency;

public interface Account {

    int getID();

    Bank getBank();

    User getUser();

    AccountType getAccountType();

    Currency getCurrency();

    double getBalance();

    void deposit(double amount);

    void withdraw(double amount);

    void transfer(double amount, Bank bankFrom, Account accountFrom, Bank bankTo, Account accountTo);

    double exchange(double amount, Currency exchangeCurrency);

    double getExchangeRate(Currency exchangeCurrency);

}
