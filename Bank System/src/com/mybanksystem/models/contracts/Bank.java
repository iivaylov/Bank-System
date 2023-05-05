package com.mybanksystem.models.contracts;

import com.mybanksystem.models.enums.City;
import com.mybanksystem.models.enums.Country;

import java.util.List;

public interface Bank {

    String getBankName();

    City getCity();

    Country getCountry();

    String getStreet();

    String getZipCode();

    List<Account> getAccounts();

    List<User> getUsers();

    void addUser(User user);

    void removeUser(User user);

    void addAccount(Account account);

    void removeAccount(Account account);

    /*Account getAccountByID(int id);*/

    /* List<Account> getAccountsByUser(User user);*/

    Account findBankAccountByID(int id);

    boolean isAccountExist(int id);


    User findUserByUsername(String username);

}
