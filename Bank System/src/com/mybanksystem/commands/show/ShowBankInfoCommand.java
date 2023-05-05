package com.mybanksystem.commands.show;

import com.mybanksystem.commands.BaseCommand;
import com.mybanksystem.core.contracts.BankSystemRepository;
import com.mybanksystem.models.contracts.Bank;
import com.mybanksystem.utils.ValidationHelpers;

import java.util.List;

public class ShowBankInfoCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    public ShowBankInfoCommand(BankSystemRepository bankSystemRepository) {
        super(bankSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        Bank bank = getBankSystemRepository().findBank(parameters.get(0));
        return showBankInfo(bank);
    }

    @Override
    protected boolean requiresLogin() {
        return false;
    }

    private String showBankInfo(Bank bank) {
        return bank.toString();
    }
}
