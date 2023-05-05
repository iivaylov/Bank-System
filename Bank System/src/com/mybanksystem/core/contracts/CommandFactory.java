package com.mybanksystem.core.contracts;

import com.mybanksystem.commands.contracts.Command;

public interface CommandFactory {

    Command createCommandFromCommandName(String commandName, BankSystemRepository bankSystemRepository);
}
