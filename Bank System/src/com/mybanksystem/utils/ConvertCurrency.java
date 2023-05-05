package com.mybanksystem.utils;

import com.mybanksystem.models.contracts.Account;
import com.mybanksystem.models.enums.Currency;

public class ConvertCurrency {

    public static double convert(double amount, Currency from, Currency to) {

        switch (from) {
            case USD:
                switch (to) {
                    case EUR:
                        return amount * 0.94;
                    case GBP:
                        return amount * 0.83;
                    case BGN:
                        return amount * 1.83;
                    case CZK:
                        return amount * 22.59;
                    case RON:
                        return amount * 4.64;
                    case RUB:
                        return amount * 72.96;
                }
            case EUR:
                switch (to) {
                    case USD:
                        return amount * 1.07;
                    case GBP:
                        return amount * 0.89;
                    case BGN:
                        return amount * 1.95;
                    case CZK:
                        return amount * 24.12;
                    case RON:
                        return amount * 4.95;
                    case RUB:
                        return amount * 76.87;
                }
            case GBP:
                switch (to) {
                    case USD:
                       return amount * 1.20;
                    case EUR:
                        return amount * 1.13;
                    case BGN:
                        return amount * 2.20;
                    case CZK:
                        return amount * 27.19;
                    case RON:
                        return amount * 5.58;
                    case RUB:
                        return amount * 87.76;
                }
            case BGN:
                switch (to) {
                    case USD:
                        return amount * 0.55;
                    case EUR:
                        return amount * 0.51;
                    case GBP:
                        return amount * 0.45;
                    case CZK:
                        return amount * 12.35;
                    case RON:
                        return amount * 2.53;
                    case RUB:
                        return amount * 39.84;
                }
            case CZK:
                switch (to) {
                    case USD:
                        return amount * 0.044;
                    case EUR:
                        return amount * 0.041;
                    case GBP:
                        return amount * 0.037;
                    case BGN:
                        return amount * 0.081;
                    case RON:
                        return amount * 0.21;
                    case RUB:
                        return amount * 3.23;
                }
            case RON:
                switch (to) {
                    case USD:
                        return amount * 0.22;
                    case EUR:
                        return amount * 0.20;
                    case GBP:
                        return amount * 0.18;
                    case BGN:
                        return amount * 0.39;
                    case CZK:
                        return amount * 4.87;
                    case RUB:
                        return amount * 15.72;
                }
            case RUB:
                switch (to) {
                    case USD:
                        return amount * 0.014;
                    case EUR:
                        return amount * 0.013;
                    case GBP:
                        return amount * 0.011;
                    case BGN:
                        return amount * 0.025;
                    case CZK:
                        return amount * 0.31;
                    case RON:
                        return amount * 0.064;
                }
        }
        return amount;
    }

    public static double getExchangeRate(Currency from, Currency to) {
        return convert(1, from, to);
    }

    public static void validateSameCurrency(double amount, Account from, Account to) {
        if (from.getCurrency() == to.getCurrency()) {
            from.withdraw(amount);
            to.deposit(amount);
        } else {
            double convertedAmount = convert(amount, from.getCurrency(), to.getCurrency());
            from.withdraw(amount);
            to.deposit(convertedAmount);
        }
    }
}
