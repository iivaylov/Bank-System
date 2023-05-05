package com.mybanksystem.models.contracts;

import com.mybanksystem.models.enums.City;
import com.mybanksystem.models.enums.Country;

import java.util.List;

public interface User {

    Bank getBank();

    String getUserName();

    String getPassword();

    String getFirstName();

    String getLastName();

    City getCity();

    Country getCountry();

    String getStreet();

    String getZipCode();

    void changePassword(String oldPassword, String newPassword);

    void changeUserName(String oldUserName, String newUserName);

    void addAccount(Account account);

    void removeAccount(Account account);

    List<Account> getAccounts();

    Account findAccountById(int id);
}
