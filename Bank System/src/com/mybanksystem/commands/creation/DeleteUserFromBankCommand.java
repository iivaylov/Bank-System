package com.mybanksystem.commands.creation;

import com.mybanksystem.commands.BaseCommand;
import com.mybanksystem.core.contracts.BankSystemRepository;
import com.mybanksystem.models.contracts.Bank;
import com.mybanksystem.models.contracts.User;
import com.mybanksystem.utils.ValidationHelpers;

import java.util.List;

public class DeleteUserFromBankCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String USER_DELETED = "User %s deleted successfully!";
    public static final String DELETE_USERS_ERROR = "You can not delete another user!";

    public DeleteUserFromBankCommand(BankSystemRepository bankSystemRepository) {
        super(bankSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        Bank bank = getBankSystemRepository().findBank(parameters.get(0));
        User user = getBankSystemRepository().findUserInBank(bank, parameters.get(1));
        throwIfUserIsDifferentThanLoggedIn(bank, user);
        bank.removeUser(user);
        logoutIfUserIsDeleted(user);
        return String.format(USER_DELETED, user.getUserName());
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    private void throwIfUserIsDifferentThanLoggedIn(Bank bank, User user) {
        if (bank.findUserByUsername(user.getUserName()) != getBankSystemRepository().getLoggedInUser()) {
            throw new IllegalArgumentException(DELETE_USERS_ERROR);
        }
    }

    private void logoutIfUserIsDeleted(User user) {
        if (user == getBankSystemRepository().getLoggedInUser()) {
            getBankSystemRepository().logout();
        }
    }
}
