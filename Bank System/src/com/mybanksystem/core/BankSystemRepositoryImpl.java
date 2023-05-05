package com.mybanksystem.core;

import com.mybanksystem.core.contracts.BankSystemRepository;
import com.mybanksystem.exceptions.ElementNotFoundException;
import com.mybanksystem.models.AccountImpl;
import com.mybanksystem.models.BankImpl;
import com.mybanksystem.models.UserImpl;
import com.mybanksystem.models.contracts.Account;
import com.mybanksystem.models.contracts.Bank;
import com.mybanksystem.models.contracts.User;
import com.mybanksystem.models.enums.AccountType;
import com.mybanksystem.models.enums.City;
import com.mybanksystem.models.enums.Country;
import com.mybanksystem.models.enums.Currency;

import java.util.ArrayList;
import java.util.List;

public class BankSystemRepositoryImpl implements BankSystemRepository {

    private static final String NO_LOGGED_IN_USER = "There is no logged in user.";
    public static final String USER_NOT_FOUND = "User %s not found in bank %s";
    public static final String ACCOUNT_NOT_FOUND = "Account %d not found in bank %s";
    private final List<Bank> banks;
    private final List<User> users;
    private int nextID;
    private User loggedUser;

    public BankSystemRepositoryImpl() {
        nextID = 0;
        loggedUser = null;
        users = new ArrayList<>();
        banks = new ArrayList<>();
    }

    @Override
    public List<Account> getBankAccounts(Bank bank) {
        return bank.getAccounts();
    }

    @Override
    public List<User> getUsers() {
        return new ArrayList<>(users);
    }

    @Override
    public void addUser(User userToAdd) {
        if (!users.contains(userToAdd)) {
            this.users.add(userToAdd);
        }
    }

    @Override
    public List<Bank> getBanks() {
        return banks;
    }

    @Override
    public void addBankAccount(Bank bank, Account account) {
        bank.addAccount(account);
    }

    @Override
    public void removeBankAccount(Bank bank, Account account) {
        bank.removeAccount(account);
    }

    @Override
    public void addBank(Bank bank) {
        banks.add(bank);
    }

    @Override
    public Bank createBank(String bankName, City city, Country country, String street, String zipCode) {
        Bank bank = new BankImpl(bankName, city, country, street, zipCode);
        banks.add(bank);
        return bank;
    }

    @Override
    public User createUser(Bank bank, String username, String password, String firstName, String lastName, City city,
                           Country country, String street, String zipCode) {
        User user = new UserImpl(bank, username, password, firstName, lastName, city, country, street, zipCode);
        users.add(user);
        return user;
    }

    @Override
    public Account createBankAccount(Bank bank, User user, AccountType accountType, Currency currency) {
        Account account = new AccountImpl(bank, ++nextID, user, accountType, currency);
        bank.addAccount(account);
        return account;
    }

    @Override
    public boolean bankExists(String bankName) {
        boolean bankExists = false;

        for (Bank bank : banks) {
            if (bank.getBankName().equals(bankName)) {
                bankExists = true;
                break;
            }
        }
        return bankExists;
    }

    @Override
    public Bank currentBank() {
        return loggedUser.getBank();
    }

    @Override
    public Bank findBank(String bankName) {
        return banks.stream()
                .filter(bank -> bank.getBankName().equals(bankName))
                .findFirst()
                .orElseThrow(() -> new ElementNotFoundException("Bank not found"));
    }

    @Override
    public User findUser(String username) {
        return users.stream()
                .filter(user -> user.getUserName().equals(username))
                .findFirst()
                .orElseThrow(() -> new ElementNotFoundException("User not found"));
    }

    @Override
    public User findUserInBank(Bank bank, String username) {
        Bank findBank = findBank(bank.getBankName());
        return findBank.getUsers().stream()
                .filter(user -> user.getUserName().equals(username))
                .findFirst()
                .orElseThrow(() -> new ElementNotFoundException(String.format(USER_NOT_FOUND,
                        username, bank.getBankName())));
    }

    @Override
    public Account findAccountInBank(Bank bank, int accountID) {
        Bank findBank = findBank(bank.getBankName());
        return findBank.getAccounts().stream()
                .filter(account -> account.getID() == accountID)
                .findFirst()
                .orElseThrow(() -> new ElementNotFoundException(String.format(ACCOUNT_NOT_FOUND
                        , accountID, bank.getBankName())));
    }

    @Override
    public User getLoggedInUser() {
        if (loggedUser == null) {
            throw new IllegalArgumentException(NO_LOGGED_IN_USER);
        }
        return loggedUser;
    }

    @Override
    public boolean hasLoggedInUser() {
        return loggedUser != null;
    }

    @Override
    public void login(User user) {
        loggedUser = user;
    }

    @Override
    public void logout() {
        loggedUser = null;
    }
}
