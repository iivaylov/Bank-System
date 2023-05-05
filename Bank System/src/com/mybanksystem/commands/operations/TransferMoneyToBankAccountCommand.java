package com.mybanksystem.commands.operations;

import com.mybanksystem.commands.BaseCommand;
import com.mybanksystem.core.contracts.BankSystemRepository;
import com.mybanksystem.models.contracts.Account;
import com.mybanksystem.models.contracts.Bank;
import com.mybanksystem.models.contracts.User;
import com.mybanksystem.utils.ParsingHelpers;
import com.mybanksystem.utils.ValidationHelpers;

import java.util.List;

public class TransferMoneyToBankAccountCommand extends BaseCommand {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 7;
    public static final String TRANSFER_MESSAGE_IF_CURRENCIES_ARE_DIFFERENT = "%s, you have successfully " +
            "transferred %.2f %s to %s, exchange rate is %s between %s and %s!";
    public static final String TRANSFER_MESSAGE_IF_CURRENCIES_ARE_EQUAL = "%s, you have successfully " +
            "transferred %.2f %s to %s!";
    public static final String SAME_ACCOUNT_ERROR = "You can't transfer money to the same account!";
    public static final String INVALID_AMOUNT = "Invalid amount";
    public static final String ONLY_FROM_YOUR_BANK_ACCOUNT = "You can transfer money only from your bank account!";

    public TransferMoneyToBankAccountCommand(BankSystemRepository bankSystemRepository) {
        super(bankSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        Bank bankFrom = getBankSystemRepository().findBank(parameters.get(0));
        User userFrom = bankFrom.findUserByUsername(parameters.get(1));
        validateUserInBank(bankFrom, userFrom);
        int userFromAccountID = ParsingHelpers.tryParseInteger(parameters.get(2), "Wrong user account ID to transfer from");
        Account accountFrom = userFrom.findAccountById(userFromAccountID);
        Bank bankTo = getBankSystemRepository().findBank(parameters.get(3));
        User userTo = bankTo.findUserByUsername(parameters.get(4));
        int userToAccountID = ParsingHelpers.tryParseInteger(parameters.get(5), "Wrong user account ID to transfer");
        Account accountTo = userTo.findAccountById(userToAccountID);
        double amount = ParsingHelpers.tryParseDouble(parameters.get(6), INVALID_AMOUNT);
        checkWhetherAccountAreSame(accountFrom, accountTo);
        accountFrom.transfer(amount, bankFrom, accountFrom, bankTo, accountTo);
        return transferMessage(accountFrom, accountTo, amount);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    private void validateUserInBank(Bank bank, User user) {
        if (bank.findUserByUsername(user.getUserName()) != getBankSystemRepository().getLoggedInUser()) {
            throw new IllegalArgumentException(ONLY_FROM_YOUR_BANK_ACCOUNT);
        }
    }

    private String transferMessage(Account accountFrom, Account accountTo, double amount) {
        if (accountFrom.getCurrency() == accountTo.getCurrency()) {
            return String.format(TRANSFER_MESSAGE_IF_CURRENCIES_ARE_EQUAL, accountFrom.getUser().getFirstName(),
                    amount, accountFrom.getCurrency(), accountTo.getUser().getFirstName());
        } else {
            return String.format(TRANSFER_MESSAGE_IF_CURRENCIES_ARE_DIFFERENT,
                    accountFrom.getUser().getFirstName(), amount, accountFrom.getCurrency(),
                    accountTo.getUser().getFirstName(), accountFrom.getExchangeRate(accountTo.getCurrency()),
                    accountFrom.getCurrency(), accountTo.getCurrency());
        }
    }

    private void checkWhetherAccountAreSame(Account accountFrom, Account accountTo) {
        if (accountFrom == accountTo) {
            throw new IllegalArgumentException(SAME_ACCOUNT_ERROR);
        }
    }
}
