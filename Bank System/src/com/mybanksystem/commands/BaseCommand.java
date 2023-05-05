package com.mybanksystem.commands;

import com.mybanksystem.commands.contracts.Command;
import com.mybanksystem.core.contracts.BankSystemRepository;

import java.util.List;

public abstract class BaseCommand implements Command {


    private final static String USER_NOT_LOGGED = "You are not logged in! Please login first!";

    private final BankSystemRepository bankSystemRepository;

    protected BaseCommand(BankSystemRepository bankSystemRepository) {
        this.bankSystemRepository = bankSystemRepository;
    }

    protected BankSystemRepository getBankSystemRepository() {
        return bankSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (requiresLogin() && !bankSystemRepository.hasLoggedInUser()) {
            throw new IllegalArgumentException(USER_NOT_LOGGED);
        }
        return executeCommand(parameters);
    }

    protected abstract boolean requiresLogin();

    protected abstract String executeCommand(List<String> parameters);
}
