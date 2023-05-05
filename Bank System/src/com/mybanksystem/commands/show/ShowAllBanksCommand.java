package com.mybanksystem.commands.show;

import com.mybanksystem.commands.BaseCommand;
import com.mybanksystem.core.contracts.BankSystemRepository;
import com.mybanksystem.models.contracts.Bank;
import com.mybanksystem.utils.ValidationHelpers;

import java.util.List;

public class ShowAllBanksCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;

    public ShowAllBanksCommand(BankSystemRepository bankSystemRepository) {
        super(bankSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        return showAllBanks();
    }

    @Override
    protected boolean requiresLogin() {
        return false;
    }

    private String showAllBanks() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("---BANKS---").append(System.lineSeparator());
        if (getBankSystemRepository().getBanks().isEmpty()) {
            stringBuilder.append("There are no banks in the system!").append(System.lineSeparator());
            stringBuilder.append("### Please create a bank first! ###");
            return stringBuilder.toString();
        }

        int count = 1;
        for (Bank bank : getBankSystemRepository().getBanks()) {
            stringBuilder.append(String.format("%d. %s%n", count, bank.getBankName()));
            count++;
        }
        return stringBuilder.toString().trim();
    }
}

