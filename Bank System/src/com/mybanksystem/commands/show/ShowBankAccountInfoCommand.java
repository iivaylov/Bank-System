package com.mybanksystem.commands.show;

import com.mybanksystem.commands.BaseCommand;
import com.mybanksystem.core.contracts.BankSystemRepository;
import com.mybanksystem.models.contracts.Account;
import com.mybanksystem.models.contracts.Bank;
import com.mybanksystem.utils.ParsingHelpers;
import com.mybanksystem.utils.ValidationHelpers;

import java.util.List;

public class ShowBankAccountInfoCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String INVALID_ACCOUNT_ID = "Invalid account ID!";
    public static final String BANK_ACCOUNT_INFO_ERROR = "You can only show information for your own accounts!";

    public ShowBankAccountInfoCommand(BankSystemRepository bankSystemRepository) {
        super(bankSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        Bank bank = getBankSystemRepository().findBank(parameters.get(0));
        int accountID = ParsingHelpers.tryParseInteger(parameters.get(1), INVALID_ACCOUNT_ID);
        int id = getBankSystemRepository().findAccountInBank(bank, accountID).getID();
        identifyAccountInBank(bank, id);
        return showBankAccountInfo(bank, id);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    private String showBankAccountInfo(Bank bank, int id) {
        Account account = getBankSystemRepository().findAccountInBank(bank, id);
        return account.toString();
    }

    private void identifyAccountInBank(Bank bank, int id) {
        if (bank.findBankAccountByID(id) !=
                getBankSystemRepository().getLoggedInUser().getBank().findBankAccountByID(id)) {
            throw new IllegalArgumentException(BANK_ACCOUNT_INFO_ERROR);
        }
    }
}
