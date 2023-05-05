package com.mybanksystem;

import com.mybanksystem.core.BankSystemEngineImpl;

public class StartUp {

    public static void main(String[] args) {

        BankSystemEngineImpl engine = new BankSystemEngineImpl();
        engine.start();
    }
}
