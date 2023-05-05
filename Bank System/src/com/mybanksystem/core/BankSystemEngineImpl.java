package com.mybanksystem.core;

import com.mybanksystem.commands.contracts.Command;
import com.mybanksystem.core.contracts.BankSystemRepository;
import com.mybanksystem.core.contracts.Engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BankSystemEngineImpl implements Engine {
    private static final String TERMINATION_COMMAND = "Exit";
    private static final String EMPTY_COMMAND = "Command cannot be empty.";
    private final CommandFactoryImpl commandFactory;
    private final BankSystemRepository bankSystemRepository;

    public BankSystemEngineImpl() {
        commandFactory = new CommandFactoryImpl();
        bankSystemRepository = new BankSystemRepositoryImpl();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String inputLine = scanner.nextLine();
                if (inputLine.isBlank()) {
                    System.out.println(EMPTY_COMMAND);
                    continue;
                }
                if (inputLine.equalsIgnoreCase(TERMINATION_COMMAND)) {
                    break;
                }
                processCommand(inputLine);
            } catch (Exception ex) {
                if (ex.getMessage() != null && !ex.getMessage().isEmpty()) {
                    System.out.println(ex.getMessage());
                } else {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    private void processCommand(String inputLine) {
        String commandName = extractCommandName(inputLine);
        Command command = commandFactory.createCommandFromCommandName(commandName, bankSystemRepository);
        List<String> parameters = extractCommandParameters(inputLine);
        String commandResult = command.execute(parameters);
        System.out.println(commandResult);
    }

    private String extractCommandName(String inputLine) {
        return inputLine.split(" ")[0];
    }

    private List<String> extractCommandParameters(String inputLine) {
        String[] commandParts = inputLine.split(" ");
        return new ArrayList<>(Arrays.asList(commandParts).subList(1, commandParts.length));
    }
}
