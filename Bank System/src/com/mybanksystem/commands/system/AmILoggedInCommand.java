package com.mybanksystem.commands.system;

import com.mybanksystem.commands.BaseCommand;
import com.mybanksystem.core.contracts.BankSystemRepository;
import com.mybanksystem.utils.ValidationHelpers;

import java.util.List;

public class AmILoggedInCommand extends BaseCommand {

    public static final int EXPECTED_ARGUMENTS = 0;

    public AmILoggedInCommand(BankSystemRepository bankSystemRepository) {
        super(bankSystemRepository);
    }

    @Override
    protected boolean requiresLogin() {
        return false;
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_ARGUMENTS);
        return amILoggedIn();
    }

    private String amILoggedIn() {
        if (!getBankSystemRepository().hasLoggedInUser()) {
            return "You are not logged in!";
        }
        return "You are logged in!";
    }
}
