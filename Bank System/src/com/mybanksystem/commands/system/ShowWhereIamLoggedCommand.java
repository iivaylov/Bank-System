package com.mybanksystem.commands.system;

import com.mybanksystem.commands.BaseCommand;
import com.mybanksystem.core.contracts.BankSystemRepository;
import com.mybanksystem.utils.ValidationHelpers;

import java.util.List;

public class ShowWhereIamLoggedCommand extends BaseCommand {

    public static final int EXPECTED_ARGUMENTS = 0;

    public ShowWhereIamLoggedCommand(BankSystemRepository bankSystemRepository) {
        super(bankSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_ARGUMENTS);
        return showWhereIamLogin();
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    private String showWhereIamLogin() {
        return String.format("You are logged in to %s bank as %s user.",
                getBankSystemRepository().getLoggedInUser().getBank().getBankName(),
                getBankSystemRepository().getLoggedInUser().getUserName());
    }
}
