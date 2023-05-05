package com.mybanksystem.commands.show;

import com.mybanksystem.commands.BaseCommand;
import com.mybanksystem.core.contracts.BankSystemRepository;
import com.mybanksystem.exceptions.InvalidUserInputException;
import com.mybanksystem.models.contracts.Bank;
import com.mybanksystem.models.contracts.User;
import com.mybanksystem.utils.ValidationHelpers;

import java.util.List;

public class ChangeUserPasswordCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 4;
    public static final String PASSWORD_CHANGED = "Password changed successfully for user %s";
    public static final String INVALID_PASSWORD = "Invalid password for user %s";
    public static final String PASSWORD_CHANGE_ERROR = "You can change only your password";

    public ChangeUserPasswordCommand(BankSystemRepository bankSystemRepository) {
        super(bankSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        Bank bank = getBankSystemRepository().findBank(parameters.get(0));
        User user = bank.findUserByUsername(parameters.get(1));
        throwIfUserIsDifferentThanLoggedIn(bank, user);
        String oldPassword = parameters.get(2);
        validatePassword(user, oldPassword);
        String newPassword = parameters.get(3);
        return changeUserPassword(bank, user, newPassword);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    private String changeUserPassword(Bank bank, User user, String newPassword) {
        bank.findUserByUsername(user.getUserName()).changePassword(user.getPassword(), newPassword);
        return String.format(PASSWORD_CHANGED, user.getUserName());
    }

    private void validatePassword(User user, String oldPassword) {
        if (!user.getPassword().equals(oldPassword)) {
            throw new InvalidUserInputException(String.format(INVALID_PASSWORD, user.getUserName()));
        }
    }

    private void throwIfUserIsDifferentThanLoggedIn(Bank bank, User user) {
        if (bank.findUserByUsername(user.getUserName()) != getBankSystemRepository().getLoggedInUser()) {
            throw new IllegalArgumentException(PASSWORD_CHANGE_ERROR);
        }
    }

}
