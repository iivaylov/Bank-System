package com.mybanksystem.models;

import com.mybanksystem.models.contracts.Account;
import com.mybanksystem.models.contracts.Bank;
import com.mybanksystem.models.contracts.User;
import com.mybanksystem.models.enums.AccountType;
import com.mybanksystem.models.enums.Currency;
import com.mybanksystem.utils.ConvertCurrency;
import com.mybanksystem.utils.ValidationHelpers;

public class AccountImpl implements Account {

    public static final int DEPOSIT_MIN = 25;
    public static final int DEPOSIT_MAX = 1000000;
    public static final String DEPOSIT_ERROR_MESSAGE = String.format("Cannot deposit less than %d or more than %s",
            DEPOSIT_MIN, DEPOSIT_MAX);
    public static final int WITHDRAW_MIN = 5;
    public static final String WITHDRAW_ERROR_MESSAGE = "Cannot withdraw more than your balance!";
    public static final int TRANSFER_MIN = 10;
    public static final String TRANSFER_ERROR_MESSAGE = "Cannot transfer more than your balance!";
    private Bank bank;
    private int id;
    private User user;
    private AccountType accountType;
    private Currency currency;
    private double balance;

    public AccountImpl(Bank bank, int id, User user, AccountType accountType, Currency currency) {
        setId(id);
        setBank(bank);
        setUser(user);
        setAccountType(accountType);
        setCurrency(currency);
        this.balance = 0;
    }

    @Override
    public Bank getBank() {
        return bank;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public AccountType getAccountType() {
        return accountType;
    }

    @Override
    public Currency getCurrency() {
        return currency;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void deposit(double amount) {
        ValidationHelpers.validateGreaterThanZero(amount);
        ValidationHelpers.validateValueInRange(amount, DEPOSIT_MIN, DEPOSIT_MAX,
                String.format(DEPOSIT_ERROR_MESSAGE, currency));
        this.balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        ValidationHelpers.validateGreaterThanZero(amount);
        ValidationHelpers.validateValueInRange(amount, WITHDRAW_MIN, balance, WITHDRAW_ERROR_MESSAGE);
        this.balance -= amount;
    }

    @Override
    public void transfer(double amount, Bank bankFrom, Account accountFrom, Bank bankTo, Account accountTo) {
        ValidationHelpers.validateGreaterThanZero(amount);
        ValidationHelpers.validateValueInRange(amount, TRANSFER_MIN, accountFrom.getBalance(), TRANSFER_ERROR_MESSAGE);
        ConvertCurrency.validateSameCurrency(amount, bankFrom.findBankAccountByID(accountFrom.getID()),
                bankTo.findBankAccountByID(accountTo.getID()));
    }

    @Override
    public double exchange(double amount, Currency exchangeCurrency) {
        ValidationHelpers.validateGreaterThanZero(amount);
        return ConvertCurrency.convert(amount, currency, exchangeCurrency);
    }

    @Override
    public double getExchangeRate(Currency exchangeCurrency) {
        return ConvertCurrency.getExchangeRate(currency, exchangeCurrency);
    }

    @Override
    public String toString() {
        return String.format("Bank: %s", bank.getBankName()) +
                System.lineSeparator() +
                String.format("Account ID: %d", id) +
                System.lineSeparator() +
                String.format("User: %s", user.getUserName()) +
                System.lineSeparator() +
                String.format("Account Type: %s", accountType) +
                System.lineSeparator() +
                String.format("Currency: %s", currency) +
                System.lineSeparator() +
                String.format("Balance: %.2f", balance) +
                System.lineSeparator();
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setUser(User user) {
        this.user = user;
    }

    private void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    private void setCurrency(Currency currency) {
        this.currency = currency;
    }

    private void setBank(Bank bank) {
        this.bank = bank;
    }

}
