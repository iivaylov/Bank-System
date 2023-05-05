package com.mybanksystem.commands.creation;

import com.mybanksystem.commands.BaseCommand;
import com.mybanksystem.core.contracts.BankSystemRepository;
import com.mybanksystem.models.contracts.Account;
import com.mybanksystem.models.contracts.Bank;
import com.mybanksystem.models.contracts.User;
import com.mybanksystem.models.enums.AccountType;
import com.mybanksystem.models.enums.Currency;
import com.mybanksystem.utils.ParsingHelpers;
import com.mybanksystem.utils.ValidationHelpers;

import java.util.List;

public class OpenBankAccountCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 4;
    public static final String ACCOUNT_TYPE_ERROR_MESSAGE = "Account type %s does not exist in the system!";
    public static final String CURRENCY_ERROR_MESSAGE = "Currency %s does not exist in the system!";
    public static final String ACCOUNT_CREATED = "Account %d created successfully!";
    public static final String ONLY_YOUR_ACCOUNT = "You can only open an account for yourself!";

    public OpenBankAccountCommand(BankSystemRepository bankSystemRepository) {
        super(bankSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        Bank bank = getBankSystemRepository().findBank(parameters.get(0));
        User user = getBankSystemRepository().findUserInBank(bank, parameters.get(1));
        throwIfUserIsDifferentThanLoggedIn(bank, user);
        AccountType accountType = ParsingHelpers.tryParseEnum(parameters.get(2), AccountType.class,
                String.format(ACCOUNT_TYPE_ERROR_MESSAGE, parameters.get(2)));
        Currency currency = ParsingHelpers.tryParseEnum(parameters.get(3), Currency.class,
                String.format(CURRENCY_ERROR_MESSAGE, parameters.get(3)));
        return openBankAccount(bank, user, accountType, currency);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    private String openBankAccount(Bank bank, User user, AccountType accountType, Currency currency) {
        Account account = getBankSystemRepository().createBankAccount(bank, user, accountType, currency);
        user.addAccount(account);
        return String.format(ACCOUNT_CREATED, account.getID());
    }

    private void throwIfUserIsDifferentThanLoggedIn(Bank bank, User user) {
        if (bank.findUserByUsername(user.getUserName()) != getBankSystemRepository().getLoggedInUser()) {
            throw new IllegalArgumentException(ONLY_YOUR_ACCOUNT);
        }
    }
}
