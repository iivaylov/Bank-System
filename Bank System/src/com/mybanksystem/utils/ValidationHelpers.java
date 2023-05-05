package com.mybanksystem.utils;

import com.mybanksystem.exceptions.InvalidUserInputException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationHelpers {

    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments. Expected: %d, Received: %d";
    public static final String INVALID_ZIP_CODE = "Zip code must be exactly 4 symbols long!";
    public static final String ZIP_CODE_PATTERN = "[0-9]{4}";

    public static void validateValueInRange(double value, double min, double max, String errorMessage) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void validateStringLength(String stringToValidate, int minLength, int maxLength, String errorMessage) {
        validateValueInRange(stringToValidate.length(), minLength, maxLength, errorMessage);
    }

    public static void validateZipCode(String zipCode) {
        Pattern pattern = Pattern.compile(ZIP_CODE_PATTERN);
        Matcher matcher = pattern.matcher(zipCode);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(INVALID_ZIP_CODE);
        }
    }

    public static void validateArgumentsCount(List<String> list, int expectedArgumentsCount) {
        if (list.size() < expectedArgumentsCount || list.size() > expectedArgumentsCount) {
            throw new IllegalArgumentException(String.format(INVALID_NUMBER_OF_ARGUMENTS, expectedArgumentsCount, list.size()));
        }
    }

    public static void validateGreaterThanZero(double value) {
        if (value <= 0) {
            throw new InvalidUserInputException();
        }
    }

    public static void validatePattern(String value, String pattern, String message) {
        Pattern patternToMatch = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = patternToMatch.matcher(value);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(message);
        }
    }

}
