package com.mybanksystem.commands.operations;

import com.mybanksystem.commands.BaseCommand;
import com.mybanksystem.core.contracts.BankSystemRepository;
import com.mybanksystem.models.contracts.Account;
import com.mybanksystem.models.contracts.Bank;
import com.mybanksystem.models.contracts.User;
import com.mybanksystem.utils.ParsingHelpers;
import com.mybanksystem.utils.ValidationHelpers;

import java.util.List;

public class DepositMoneyInBankAccountCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 4;
    public static final String DEPOSIT_MONEY_SUCCESSFULLY = "%s, you have successfully deposited money in your bank account!";
    public static final String DEPOSIT_MONEY_ONLY_IN_YOUR_ACCOUNT = "You can deposit money only in your bank account!";

    public DepositMoneyInBankAccountCommand(BankSystemRepository bankSystemRepository) {
        super(bankSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        Bank bank = getBankSystemRepository().findBank(parameters.get(0));
        User user = bank.findUserByUsername(parameters.get(1));
        validateUserInBank(bank, user);
        int accountID = ParsingHelpers.tryParseInteger(parameters.get(2), "This account ID is invalid");
        Account account = user.findAccountById(accountID);
        double amount = ParsingHelpers.tryParseDouble(parameters.get(3), "This amount is invalid");
        account.deposit(amount);
        return String.format(DEPOSIT_MONEY_SUCCESSFULLY, account.getUser().getFirstName());
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    private void validateUserInBank(Bank bank, User user) {
        if (bank.findUserByUsername(user.getUserName()) != getBankSystemRepository().getLoggedInUser()) {
            throw new IllegalArgumentException(DEPOSIT_MONEY_ONLY_IN_YOUR_ACCOUNT);
        }
    }
}
