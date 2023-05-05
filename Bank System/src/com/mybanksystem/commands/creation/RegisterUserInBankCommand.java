package com.mybanksystem.commands.creation;

import com.mybanksystem.commands.BaseCommand;
import com.mybanksystem.core.contracts.BankSystemRepository;
import com.mybanksystem.models.contracts.Bank;
import com.mybanksystem.models.contracts.User;
import com.mybanksystem.models.enums.City;
import com.mybanksystem.models.enums.Country;
import com.mybanksystem.utils.ParsingHelpers;
import com.mybanksystem.utils.ValidationHelpers;

import java.util.List;

public class RegisterUserInBankCommand extends BaseCommand {

    private static final String USER_REGISTERED = "User %s registered successfully!";
    public static final String USER_LOGGED_IN_ALREADY = "User %s is logged in! Please log out first!";
    private static final String USER_ALREADY_EXIST = "User %s already exist. Choose a different username!";
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 9;
    public static final String CITY_ERROR_MESSAGE = "City %s does not exist in the system!";
    public static final String COUNTRY_ERROR_MESSAGE = "Country %s does not exist in the system!";

    public RegisterUserInBankCommand(BankSystemRepository bankSystemRepository) {
        super(bankSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        throwIfUserLoggedIn();
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        Bank bank = getBankSystemRepository().findBank(parameters.get(0));
        String username = parameters.get(1);
        String password = parameters.get(2);
        String firstName = parameters.get(3);
        String lastName = parameters.get(4);
        City city = ParsingHelpers.tryParseEnum(parameters.get(5), City.class,
                String.format(CITY_ERROR_MESSAGE, parameters.get(5)));
        Country country = ParsingHelpers.tryParseEnum(parameters.get(6), Country.class,
                String.format(COUNTRY_ERROR_MESSAGE, parameters.get(6)));
        String street = parameters.get(7);
        String zipCode = parameters.get(8);

        return registerUser(bank, username, password, firstName, lastName, city, country, street, zipCode);
    }

    @Override
    protected boolean requiresLogin() {
        return false;
    }

    private String registerUser(Bank bank, String username, String password, String firstName, String lastName,
                                City city, Country country, String street, String zipCode) {
        throwIfUsernameAlreadyExistsInBank(bank, username);
        User user = getBankSystemRepository().createUser(bank, username, password, firstName,
                lastName, city, country, street, zipCode);
        bank.addUser(user);
        getBankSystemRepository().login(user);
        getBankSystemRepository().addUser(user);

        return String.format(USER_REGISTERED, username);
    }

    private void throwIfUserLoggedIn() {
        if (getBankSystemRepository().hasLoggedInUser()) {
            throw new IllegalArgumentException(
                    String.format(USER_LOGGED_IN_ALREADY, getBankSystemRepository().getLoggedInUser().getUserName()));
        }
    }

    private void throwIfUsernameAlreadyExistsInBank(Bank bank, String username) {
        if (bank.getUsers().stream().anyMatch(u -> u.getUserName().equalsIgnoreCase(username))) {
            throw new IllegalArgumentException(String.format(USER_ALREADY_EXIST, username));
        }
    }
}
