package com.mybanksystem.commands.creation;

import com.mybanksystem.commands.BaseCommand;
import com.mybanksystem.core.contracts.BankSystemRepository;
import com.mybanksystem.models.contracts.Bank;
import com.mybanksystem.models.enums.City;
import com.mybanksystem.models.enums.Country;
import com.mybanksystem.utils.ParsingHelpers;
import com.mybanksystem.utils.ValidationHelpers;

import java.util.List;

public class CreateBankCommand extends BaseCommand {

    public final static String BANK_CREATED = "Bank %s created successfully!";
    public final static String BANK_ALREADY_EXISTS = "Bank %s already exists!";

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 5;
    public static final String CITY_ERROR_MESSAGE = "City %s does not exist in the system!";
    public static final String COUNTRY_ERROR_MESSAGE = "Country %s does not exist in the system!";

    public CreateBankCommand(BankSystemRepository bankSystemRepository) {
        super(bankSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String bankName = parameters.get(0);
        City city = ParsingHelpers.tryParseEnum(parameters.get(1), City.class,
                String.format(CITY_ERROR_MESSAGE, parameters.get(1)));
        Country country = ParsingHelpers.tryParseEnum(parameters.get(2), Country.class,
                String.format(COUNTRY_ERROR_MESSAGE, parameters.get(2)));
        String street = parameters.get(3);
        String zipCode = parameters.get(4);
        return createBank(bankName, city, country, street, zipCode);
    }

    @Override
    protected boolean requiresLogin() {
        return false;
    }

    private String createBank(String bankName, City city, Country country, String street, String zipCode) {
        if (getBankSystemRepository().getBanks().stream().anyMatch(b -> b.getBankName().equalsIgnoreCase(bankName))) {
            return String.format(BANK_ALREADY_EXISTS, bankName);
        }
        Bank bank = getBankSystemRepository().createBank(bankName, city, country, street, zipCode);
        return String.format(BANK_CREATED, bank.getBankName());
    }

}
