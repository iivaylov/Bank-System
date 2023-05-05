package com.mybanksystem.commands.system;

import com.mybanksystem.commands.BaseCommand;
import com.mybanksystem.core.contracts.BankSystemRepository;

import java.util.List;

public class LogoutCommand extends BaseCommand {

    public static final String USER_LOGGED_OUT = "You logged out!";

    public LogoutCommand(BankSystemRepository bankSystemRepository) {
        super(bankSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        getBankSystemRepository().logout();
        return USER_LOGGED_OUT;
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}
