package com.mybanksystem.core.contracts;

import com.mybanksystem.models.contracts.Account;
import com.mybanksystem.models.contracts.Bank;
import com.mybanksystem.models.contracts.User;
import com.mybanksystem.models.enums.AccountType;
import com.mybanksystem.models.enums.City;
import com.mybanksystem.models.enums.Country;
import com.mybanksystem.models.enums.Currency;

import java.util.List;

public interface BankSystemRepository {

    List<Account> getBankAccounts(Bank bank);

    List<User> getUsers();

    void addUser(User user);

    List<Bank> getBanks();

    void addBankAccount(Bank bank, Account account);

    void removeBankAccount(Bank bank, Account account);

    void addBank(Bank bank);

    Bank createBank(String bankName, City city, Country country, String street, String zipCode);

    User createUser(Bank bank, String username, String password, String firstName, String lastName,
                    City city, Country country, String street, String zipCode);

    Account createBankAccount(Bank bank, User user, AccountType accountType, Currency currency);

    boolean bankExists(String bankName);


    Bank currentBank();


    /*    boolean bankAccountExists(Bank bank, int accountID);*/

//    void transferMoney(Bank bank, int fromAccountID, int toAccountID, double amount);
//

    //TESTING
/*    void depositMoney(Bank bank, int accountID, double amount);

    void withdrawMoney(Bank bank, int accountID, double amount);*/

    /*    Account findBankAccount(Bank bank,Account account);*/

    Bank findBank(String bankName);

    User findUser(String username);

    // User findUserInBankAccounts(Bank bank, String username);

    User findUserInBank(Bank bank, String username);

    Account findAccountInBank(Bank bank, int accountID);

    User getLoggedInUser();

    boolean hasLoggedInUser();

    void login(User user);

    void logout();

}
