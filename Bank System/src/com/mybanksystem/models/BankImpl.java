package com.mybanksystem.models;

import com.mybanksystem.exceptions.ElementNotFoundException;
import com.mybanksystem.exceptions.InvalidUserInputException;
import com.mybanksystem.models.contracts.Account;
import com.mybanksystem.models.contracts.Bank;
import com.mybanksystem.models.contracts.User;
import com.mybanksystem.models.enums.City;
import com.mybanksystem.models.enums.Country;
import com.mybanksystem.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class BankImpl implements Bank {

    public static final int BANK_NAME_MIN_LENGTH = 3;
    public static final int BANK_NAME_MAX_LENGTH = 30;
    public static final String BANK_NAME_ERR =
            format("Bank name must be between %d and %d symbols long!",
                    BANK_NAME_MIN_LENGTH, BANK_NAME_MAX_LENGTH);
    public static final int STREET_MIN_LENGTH = 3;
    public static final int STREET_MAX_LENGTH = 50;
    public static final String STREET_ERROR_MESSAGE =
            format("Street name must be between %d and %d symbols long!",
                    STREET_MIN_LENGTH, STREET_MAX_LENGTH);
    private String bankName;
    private City city;
    private Country country;
    private String street;
    private String zipCode;
    private final List<Account> accounts;
    private final List<User> users;

    public BankImpl(String bankName, City city, Country country, String street, String zipCode) {
        setBankName(bankName);
        setCity(city);
        setCountry(country);
        setStreet(street);
        setZipCode(zipCode);
        users = new ArrayList<>();
        accounts = new ArrayList<>();
    }

    @Override
    public String getBankName() {
        return bankName;
    }

    @Override
    public City getCity() {
        return city;
    }

    @Override
    public Country getCountry() {
        return country;
    }

    @Override
    public String getStreet() {
        return street;
    }

    @Override
    public String getZipCode() {
        return zipCode;
    }

    @Override
    public List<Account> getAccounts() {
        return new ArrayList<>(accounts);
    }

    @Override
    public List<User> getUsers() {
        return new ArrayList<>(users);
    }

    @Override
    public void addUser(User user) {
        if (users.contains(user)) {
            throw new InvalidUserInputException("User already exists!");
        }
        users.add(user);
    }

    @Override
    public void removeUser(User user) {
        if (!users.contains(user)) {
            throw new ElementNotFoundException("User not found!");
        }
        users.remove(user);
    }

    @Override
    public void addAccount(Account account) {
        if (isAccountExist(account.getID())) {
            throw new InvalidUserInputException("Account with this ID already exist!");
        }
        accounts.add(account);
    }

    @Override
    public void removeAccount(Account account) {
        if (!isAccountExist(account.getID())) {
            throw new ElementNotFoundException("Account with this ID does not exist!");
        }
        accounts.remove(account);
    }

    @Override
    public Account findBankAccountByID(int id) {
        return accounts.stream()
                .filter(a -> a.getID() == id)
                .findFirst()
                .orElseThrow(() -> new ElementNotFoundException("Account with this ID does not exist!"));
    }

    @Override
    public boolean isAccountExist(int id) {
        boolean isExist = false;

        for (Account account : accounts) {
            if (account.getID() == id) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }

    @Override
    public User findUserByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUserName().equals(username))
                .findFirst()
                .orElseThrow(() -> new ElementNotFoundException("User with this username does not exist!"));
    }

    @Override
    public String toString() {
        return String.format("Bank name: %s", bankName) +
                System.lineSeparator() +
                String.format("City: %s", city) +
                System.lineSeparator() +
                String.format("Country: %s", country) +
                System.lineSeparator() +
                String.format("Street: %s", street) +
                System.lineSeparator() +
                String.format("Zip code: %s", zipCode);
    }

    private void setBankName(String bankName) {
        ValidationHelpers.validateStringLength(bankName, BANK_NAME_MIN_LENGTH, BANK_NAME_MAX_LENGTH, BANK_NAME_ERR);
        this.bankName = bankName;
    }

    private void setCity(City city) {
        this.city = city;
    }

    private void setCountry(Country country) {
        this.country = country;
    }

    private void setStreet(String street) {
        ValidationHelpers.validateStringLength(street, STREET_MIN_LENGTH, STREET_MAX_LENGTH, STREET_ERROR_MESSAGE);
        this.street = street;
    }

    private void setZipCode(String zipCode) {
        ValidationHelpers.validateZipCode(zipCode);
        this.zipCode = zipCode;
    }
}
