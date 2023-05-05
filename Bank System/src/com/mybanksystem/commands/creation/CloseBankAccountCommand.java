package com.mybanksystem.commands.creation;

import com.mybanksystem.commands.BaseCommand;
import com.mybanksystem.core.contracts.BankSystemRepository;
import com.mybanksystem.models.contracts.Account;
import com.mybanksystem.models.contracts.Bank;
import com.mybanksystem.models.contracts.User;
import com.mybanksystem.utils.ParsingHelpers;
import com.mybanksystem.utils.ValidationHelpers;

import java.util.List;

public class CloseBankAccountCommand extends BaseCommand {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    public static final String ACCOUNT_ID_ERROR = "Account ID must be a number!";
    public static final String ACCOUNT_CLOSED = "Account %d closed successfully!";
    public static final String ONLY_CLOSE_YOUR_ACCOUNT_ERROR = "You can only close an account for yourself!";

    public CloseBankAccountCommand(BankSystemRepository bankSystemRepository) {
        super(bankSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        Bank bank = getBankSystemRepository().findBank(parameters.get(0));
        User user = getBankSystemRepository().findUserInBank(bank, parameters.get(1));
        throwIfUserIsDifferentThanLoggedIn(bank, user);
        int accountIDtoClose = ParsingHelpers.tryParseInteger(parameters.get(2), ACCOUNT_ID_ERROR);
        Account account = user.findAccountById(accountIDtoClose);
        return closeBankAccount(user, account);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    private String closeBankAccount(User user, Account account) {
        user.removeAccount(account);
        return String.format(ACCOUNT_CLOSED, account.getID());
    }

    private void throwIfUserIsDifferentThanLoggedIn(Bank bank, User user) {
        if (bank.findUserByUsername(user.getUserName()) != getBankSystemRepository().getLoggedInUser()) {
            throw new IllegalArgumentException(ONLY_CLOSE_YOUR_ACCOUNT_ERROR);
        }
    }


}
