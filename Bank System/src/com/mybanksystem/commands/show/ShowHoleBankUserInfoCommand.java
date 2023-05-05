package com.mybanksystem.commands.show;

import com.mybanksystem.commands.BaseCommand;
import com.mybanksystem.core.contracts.BankSystemRepository;
import com.mybanksystem.models.contracts.Account;
import com.mybanksystem.models.contracts.Bank;
import com.mybanksystem.models.contracts.User;
import com.mybanksystem.utils.ValidationHelpers;

import java.util.List;

public class ShowHoleBankUserInfoCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String YOUR_HOLE_INFO_ERROR = "You can see only your hole info";

    public ShowHoleBankUserInfoCommand(BankSystemRepository bankSystemRepository) {
        super(bankSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        Bank bank = getBankSystemRepository().findBank(parameters.get(0));
        User user = getBankSystemRepository().findUserInBank(bank, parameters.get(1));
        validateUserInBank(bank, user);
        return showHoleBankUserInfo(user);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    private void validateUserInBank(Bank bank, User user) {
        if (bank.findUserByUsername(user.getUserName()) != getBankSystemRepository().getLoggedInUser()) {
            throw new IllegalArgumentException(YOUR_HOLE_INFO_ERROR);
        }
    }

    private String showHoleBankUserInfo(User user) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("--- User: { %s }---", user.getFirstName())).append(System.lineSeparator());
        stringBuilder.append(String.format("%s", user)).append(System.lineSeparator());
        int count = 1;
        if (user.getAccounts().isEmpty()) {
            stringBuilder.append("### No accounts found ###");
        } else {
            stringBuilder.append(System.lineSeparator())
                    .append("### ACCOUNTS ###")
                    .append(System.lineSeparator());
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
