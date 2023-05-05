package com.mybanksystem.commands.show;

import com.mybanksystem.commands.BaseCommand;
import com.mybanksystem.core.contracts.BankSystemRepository;
import com.mybanksystem.models.contracts.Account;
import com.mybanksystem.models.contracts.Bank;
import com.mybanksystem.models.contracts.User;
import com.mybanksystem.utils.ValidationHelpers;

import java.util.List;

public class ShowUserAccountsCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String YOUR_ACCOUNT_ERROR = "You can only view your own accounts!";

    public ShowUserAccountsCommand(BankSystemRepository bankSystemRepository) {
        super(bankSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        Bank bank = getBankSystemRepository().findBank(parameters.get(0));
        User user = getBankSystemRepository().findUserInBank(bank, parameters.get(1));
        throwIfUserIsDifferentThanLoggedIn(bank, user);
        return showUserAccounts(user);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    private void throwIfUserIsDifferentThanLoggedIn(Bank bank, User user) {
        if (bank.findUserByUsername(user.getUserName()) != getBankSystemRepository().getLoggedInUser()) {
            throw new IllegalArgumentException(YOUR_ACCOUNT_ERROR);
        }
    }

    private String showUserAccounts(User user) {
        StringBuilder stringBuilder = new StringBuilder();
        if (user.getAccounts().isEmpty()) {
            stringBuilder.append("### No accounts found ###");
        } else {
            int count = 1;
            for (Account account : user.getAccounts()) {
                stringBuilder.append(String.format("Account --- %d ---", count))
                        .append(System.lineSeparator())
                        .append(String.format("%s", account))
                        .append(System.lineSeparator());
                count++;
            }
        }
        return stringBuilder.toString().trim();
    }
}
