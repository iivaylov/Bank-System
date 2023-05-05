package com.mybanksystem.commands.operations;

import com.mybanksystem.commands.BaseCommand;
import com.mybanksystem.core.contracts.BankSystemRepository;
import com.mybanksystem.models.contracts.Account;
import com.mybanksystem.models.contracts.Bank;
import com.mybanksystem.models.contracts.User;
import com.mybanksystem.utils.ParsingHelpers;
import com.mybanksystem.utils.ValidationHelpers;

import java.util.List;

public class WithdrawMoneyFromBankAccountCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 4;
    public static final String WITHDRAW_SUCCESSFULLY = "%s, you have successfully withdrawn money from your bank account!";
    public static final String WITHDRAW_MONEY_ERROR = "You can withdraw money only from your bank account!";

    public WithdrawMoneyFromBankAccountCommand(BankSystemRepository bankSystemRepository) {
        super(bankSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        Bank bank = getBankSystemRepository().findBank(parameters.get(0));
        User user = bank.findUserByUsername(parameters.get(1));
        validateUserInBank(bank, user);
        int accountID = ParsingHelpers.tryParseInteger(parameters.get(2), "your account ID is invalid");
        Account account = user.findAccountById(accountID);
        double amount = ParsingHelpers.tryParseDouble(parameters.get(3), "your amount is invalid");
        account.withdraw(amount);
        return String.format(WITHDRAW_SUCCESSFULLY, account.getUser().getFirstName());
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    private void validateUserInBank(Bank bank, User user) {
        if (bank.findUserByUsername(user.getUserName()) != getBankSystemRepository().getLoggedInUser()) {
            throw new IllegalArgumentException(WITHDRAW_MONEY_ERROR);
        }
    }
}
