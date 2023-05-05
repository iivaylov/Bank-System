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

public class UserImpl implements User {
    public static final int USERNAME_LEN_MIN = 2;
    public static final int USERNAME_LEN_MAX = 20;
    private static final String USERNAME_REGEX_PATTERN = "^[A-Za-z0-9]+$";
    private static final String USERNAME_PATTERN_ERR = "Username contains invalid symbols!";
    private static final String USERNAME_LEN_ERR = format(
            "Username must be between %d and %d characters long!",
            USERNAME_LEN_MIN,
            USERNAME_LEN_MAX);

    public static final int PASSWORD_LEN_MIN = 5;
    public static final int PASSWORD_LEN_MAX = 30;
    private static final String PASSWORD_REGEX_PATTERN = "^[A-Za-z0-9@*_-]+$";
    private static final String PASSWORD_PATTERN_ERR = "Password contains invalid symbols!";
    private static final String PASSWORD_LEN_ERR = format(
            "Password must be between %s and %s characters long!",
            PASSWORD_LEN_MIN,
            PASSWORD_LEN_MAX);

    public static final int LASTNAME_LEN_MIN = 2;
    public static final int LASTNAME_LEN_MAX = 20;
    private static final String LASTNAME_LEN_ERR = format(
            "Lastname must be between %s and %s characters long!",
            LASTNAME_LEN_MIN,
            LASTNAME_LEN_MAX);

    public static final int FIRSTNAME_LEN_MIN = 2;
    public static final int FIRSTNAME_LEN_MAX = 20;
    private static final String FIRSTNAME_LEN_ERR = format(
            "Firstname must be between %s and %s characters long!",
            FIRSTNAME_LEN_MIN,
            FIRSTNAME_LEN_MAX);
    public static final String SAME_PASSWORD_ERROR = "The new password must be different from the old one!";
    public static final String SAME_USER_ERROR = "The new username must be different from the old one!";

    public static final int STREET_MIN_LENGTH = 3;
    public static final int STREET_MAX_LENGTH = 50;
    private static final String STREET_ERROR_MESSAGE =
            format("Street name must be between %d and %d symbols long!",
                    STREET_MIN_LENGTH, STREET_MAX_LENGTH);
    private Bank bank;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private City city;
    private Country country;
    private String street;
    private String zipCode;
    private final List<Account> accounts;

    public UserImpl(Bank bank, String username, String password, String firstName, String lastName,
                    City city, Country country, String street, String zipCode) {
        setBank(bank);
        setUsername(username);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
        setCity(city);
        setCountry(country);
        setStreet(street);
        setZipCode(zipCode);
        accounts = new ArrayList<>();
    }


    @Override
    public Bank getBank() {
        return bank;
    }

    @Override
    public String getUserName() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
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
    public void changePassword(String oldPassword, String newPassword) {
        if (oldPassword.equalsIgnoreCase(newPassword)) {
            throw new InvalidUserInputException(SAME_PASSWORD_ERROR);
        }
        setPassword(newPassword);
    }

    @Override
    public void changeUserName(String oldUserName, String newUserName) {
        if (oldUserName.equalsIgnoreCase(newUserName)) {
            throw new InvalidUserInputException(SAME_USER_ERROR);
        }
        setUsername(newUserName);
    }

    @Override
    public void addAccount(Account account) {
        accounts.add(account);
    }

    @Override
    public void removeAccount(Account account) {
        if (!accounts.contains(account)) {
            throw new ElementNotFoundException("Account not found!");
        }
        accounts.remove(account);
    }

    @Override
    public List<Account> getAccounts() {
        return new ArrayList<>(accounts);
    }

    @Override
    public Account findAccountById(int id) {
        return accounts.stream()
                .filter(a -> a.getID() == id)
                .findFirst()
                .orElseThrow(() -> new ElementNotFoundException("Account not found!"));
    }

    @Override
    public String toString() {
        return String.format("Username: %s", username) +
                System.lineSeparator() +
                String.format("First name: %s", firstName) +
                System.lineSeparator() +
                String.format("Last name: %s", lastName) +
                System.lineSeparator() +
                String.format("City: %s", city) +
                System.lineSeparator() +
                String.format("Country: %s", country) +
                System.lineSeparator() +
                String.format("Street: %s", street) +
                System.lineSeparator() +
                String.format("Zip code: %s", zipCode);
    }

    private void setBank(Bank bank) {
        this.bank = bank;
    }

    private void setUsername(String username) {
        ValidationHelpers.validatePattern(username, USERNAME_REGEX_PATTERN, USERNAME_PATTERN_ERR);
        ValidationHelpers.validateStringLength(username, USERNAME_LEN_MIN, USERNAME_LEN_MAX, USERNAME_LEN_ERR);
        this.username = username;
    }

    private void setPassword(String password) {
        ValidationHelpers.validatePattern(password, PASSWORD_REGEX_PATTERN, PASSWORD_PATTERN_ERR);
        ValidationHelpers.validateStringLength(password, PASSWORD_LEN_MIN, PASSWORD_LEN_MAX, PASSWORD_LEN_ERR);
        this.password = password;
    }

    private void setFirstName(String firstName) {
        ValidationHelpers.validateStringLength(firstName, FIRSTNAME_LEN_MIN, FIRSTNAME_LEN_MAX, FIRSTNAME_LEN_ERR);
        this.firstName = firstName;
    }

    private void setLastName(String lastName) {
        ValidationHelpers.validateStringLength(lastName, LASTNAME_LEN_MIN, LASTNAME_LEN_MAX, LASTNAME_LEN_ERR);
        this.lastName = lastName;
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
