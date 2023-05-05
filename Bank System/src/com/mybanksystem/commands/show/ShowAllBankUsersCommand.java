package com.mybanksystem.commands.show;

import com.mybanksystem.commands.BaseCommand;
import com.mybanksystem.core.contracts.BankSystemRepository;
import com.mybanksystem.models.contracts.Bank;
import com.mybanksystem.models.contracts.User;
import com.mybanksystem.utils.ValidationHelpers;

import java.util.List;

public class ShowAllBankUsersCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    public ShowAllBankUsersCommand(BankSystemRepository bankSystemRepository) {
        super(bankSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        Bank bank = getBankSystemRepository().findBank(parameters.get(0));
        return showAllUsers(bank);
    }

    @Override
    protected boolean requiresLogin() {
        return false;
    }

    private String showAllUsers(Bank bank) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("---USERS IN BANK { %s }---", bank.getBankName()))
                .append(System.lineSeparator());
        if (bank.getUsers().isEmpty()) {
            stringBuilder.append("### There are no users in the bank! ###");
            return stringBuilder.toString();
        }
        int counter = 1;
        for (User user : bank.getUsers()) {
            stringBuilder.append(String.format("%d. %s", counter, user.getUserName())).append(System.lineSeparator());
            counter++;
        }
        return stringBuilder.toString().trim();
    }
}


