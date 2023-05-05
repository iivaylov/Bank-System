package com.mybanksystem.core;

import com.mybanksystem.commands.contracts.Command;
import com.mybanksystem.commands.creation.*;
import com.mybanksystem.commands.enums.CommandType;
import com.mybanksystem.commands.operations.DepositMoneyInBankAccountCommand;
import com.mybanksystem.commands.operations.TransferMoneyToBankAccountCommand;
import com.mybanksystem.commands.operations.WithdrawMoneyFromBankAccountCommand;
import com.mybanksystem.commands.show.*;
import com.mybanksystem.commands.system.AmILoggedInCommand;
import com.mybanksystem.commands.system.LoginCommand;
import com.mybanksystem.commands.system.LogoutCommand;
import com.mybanksystem.commands.system.ShowWhereIamLoggedCommand;
import com.mybanksystem.core.contracts.BankSystemRepository;
import com.mybanksystem.core.contracts.CommandFactory;
import com.mybanksystem.utils.ParsingHelpers;

public class CommandFactoryImpl implements CommandFactory {

    private static final String INVALID_COMMAND = "Invalid command name: %s!";

    @Override
    public Command createCommandFromCommandName(String commandName, BankSystemRepository bankSystemRepository) {
        CommandType commandType = ParsingHelpers.tryParseEnum(commandName, CommandType.class,
                String.format(INVALID_COMMAND, commandName));

        switch (commandType) {
            case CREATEBANK:
                return new CreateBankCommand(bankSystemRepository);
            case REGISTERUSERINBANK:
                return new RegisterUserInBankCommand(bankSystemRepository);
            case LOGIN:
                return new LoginCommand(bankSystemRepository);
            case LOGOUT:
                return new LogoutCommand(bankSystemRepository);
            case OPENBANKACCOUNT:
                return new OpenBankAccountCommand(bankSystemRepository);
            case SHOWALLBANKS:
                return new ShowAllBanksCommand(bankSystemRepository);
            case SHOWBANKINFO:
                return new ShowBankInfoCommand(bankSystemRepository);
            case SHOWALLBANKUSERS:
                return new ShowAllBankUsersCommand(bankSystemRepository);
            case SHOWBANKACCOUNTINFO:
                return new ShowBankAccountInfoCommand(bankSystemRepository);
            case SHOWHOLEBANKUSERINFO:
                return new ShowHoleBankUserInfoCommand(bankSystemRepository);
            case SHOWBANKUSERINFO:
                return new ShowBankUserInfoCommand(bankSystemRepository);
            case DEPOSITMONEYINBANKACCOUNT:
                return new DepositMoneyInBankAccountCommand(bankSystemRepository);
            case WITHDRAWMONEYFROMBANKACCOUNT:
                return new WithdrawMoneyFromBankAccountCommand(bankSystemRepository);
            case SHOWWHEREIAMLOGGEDIN:
                return new ShowWhereIamLoggedCommand(bankSystemRepository);
            case CHANGEUSERPASSWORD:
                return new ChangeUserPasswordCommand(bankSystemRepository);
            case SHOWUSERPASSWORD:
                return new ShowUserPasswordCommand(bankSystemRepository);
            case AMILOGGEDIN:
                return new AmILoggedInCommand(bankSystemRepository);
            case TRANSFERMONEYTOBANKACCOUNT:
                return new TransferMoneyToBankAccountCommand(bankSystemRepository);
            case CLOSEBANKACCOUNT:
                return new CloseBankAccountCommand(bankSystemRepository);
            case SHOWUSERACCOUNTS:
                return new ShowUserAccountsCommand(bankSystemRepository);
            case DELETEUSERFROMBANK:
                return new DeleteUserFromBankCommand(bankSystemRepository);
        }
        throw new IllegalArgumentException(String.format(INVALID_COMMAND, commandName));
    }
}
