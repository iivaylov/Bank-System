package com.mybanksystem.commands.show;

import com.mybanksystem.commands.BaseCommand;
import com.mybanksystem.core.contracts.BankSystemRepository;
import com.mybanksystem.models.contracts.Bank;
import com.mybanksystem.models.contracts.User;
import com.mybanksystem.utils.ValidationHelpers;

import java.util.List;

public class ShowBankUserInfoCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String YOUR_INFO_ERROR = "You can see only your info";

    public ShowBankUserInfoCommand(BankSystemRepository bankSystemRepository) {
        super(bankSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        Bank bank = getBankSystemRepository().findBank(parameters.get(0));
        User user = bank.findUserByUsername(parameters.get(1));
        validateUserInBank(bank, user);
        return showBankUserInfo(user);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    private void validateUserInBank(Bank bank, User user) {
        if (bank.findUserByUsername(user.getUserName()) != getBankSystemRepository().getLoggedInUser()) {
            throw new IllegalArgumentException(YOUR_INFO_ERROR);
        }
    }

    private String showBankUserInfo(User user) {
        return String.format("--- User: { %s }---", user.getFirstName()) +
                System.lineSeparator() +
                String.format("%s", user);
    }
}
