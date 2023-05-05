package com.mybanksystem.commands.show;

import com.mybanksystem.commands.BaseCommand;
import com.mybanksystem.core.contracts.BankSystemRepository;
import com.mybanksystem.models.contracts.Bank;
import com.mybanksystem.models.contracts.User;
import com.mybanksystem.utils.ValidationHelpers;

import java.util.List;

public class ShowUserPasswordCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String PASSWORD_ERROR = "You can see only your password";
    public static final String SHOW_PASSWORD = "Password for user %s is { %s }";

    public ShowUserPasswordCommand(BankSystemRepository bankSystemRepository) {
        super(bankSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        Bank bank = getBankSystemRepository().findBank(parameters.get(0));
        User user = bank.findUserByUsername(parameters.get(1));
        throwIfUserIsDifferentThanLoggedIn(bank, user);
        return showUserPassword(user);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    private String showUserPassword(User user) {
        return String.format(SHOW_PASSWORD, user.getUserName(), user.getPassword());
    }

    private void throwIfUserIsDifferentThanLoggedIn(Bank bank, User user) {
        if (bank.findUserByUsername(user.getUserName()) != getBankSystemRepository().getLoggedInUser()) {
            throw new IllegalArgumentException(PASSWORD_ERROR);
        }
    }
}
