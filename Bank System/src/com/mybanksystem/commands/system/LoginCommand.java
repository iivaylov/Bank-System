package com.mybanksystem.commands.system;

import com.mybanksystem.commands.BaseCommand;
import com.mybanksystem.core.contracts.BankSystemRepository;
import com.mybanksystem.models.contracts.Bank;
import com.mybanksystem.models.contracts.User;
import com.mybanksystem.utils.ValidationHelpers;

import java.util.List;

public class LoginCommand extends BaseCommand {


    private static final String USER_LOGGED_IN = "User %s successfully logged in!";
    private static final String WRONG_USERNAME_OR_PASSWORD = "Wrong username or password!";
    public static final String USER_LOGGED_IN_ALREADY = "User %s is logged in! Please log out first!";

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    public LoginCommand(BankSystemRepository bankSystemRepository) {
        super(bankSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        throwIfUserLoggedIn();
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        Bank bank = getBankSystemRepository().findBank(parameters.get(0));
        String username = parameters.get(1);
        String password = parameters.get(2);

        return login(bank, username, password);
    }

    private String login(Bank bank, String username, String password) {
        User userFound = getBankSystemRepository().findUserInBank(bank, username);

        if (!userFound.getPassword().equals(password)) {
            throw new IllegalArgumentException(WRONG_USERNAME_OR_PASSWORD);
        }

        getBankSystemRepository().login(userFound);
        return String.format(USER_LOGGED_IN, username);
    }

    @Override
    protected boolean requiresLogin() {
        return false;
    }

    private void throwIfUserLoggedIn() {
        if (getBankSystemRepository().hasLoggedInUser()) {
            throw new IllegalArgumentException(
                    String.format(USER_LOGGED_IN_ALREADY, getBankSystemRepository().getLoggedInUser().getUserName())
            );
        }
    }
}
