package com.ftn.railwayapp.util;

public class ErrorMessages {

    public static final String WRONG_ID = "Id cannot be empty.";
    public static final String WRONG_EMAIL = "Email is not in the right format.";
    public static final String WRONG_NAME = "Name must be between 4 and 20 characters.";
    public static final String EMPTY_EMAIL = "Email cannot be empty.";
    public static final String WRONG_PASSWORD =
            "Password must contain at least 6 characters. " +
                    "At least one number and one special character.";
    public static final String WRONG_OLD_PASSWORD =
            "Old password must contain at least 6 characters. " +
                    "At least one number and one special character.";
    public static final String WRONG_CONFIRM_PASSWORD =
            "Confirm password must contain at least 6 characters. " +
                    "At least one number and one special character. Your old password is not match.";
    public static final String PASSWORD_NOT_LONG_ENOUGH = "Password must be between 6 and 50 characters long.";
    public static final String INVALID_SOCIAL_TOKEN = "Social token is invalid.";
    public static final String WRONG_GENDER = "Gender must be selected.";
    public static final String WRONG_FULL_NAME = "Full name must contain only letters and cannot be too long/empty.";
    public static final String WRONG_CITY = "City must contain only letters and cannot be too long/empty.";
    public static final String WRONG_STREET = "Street must contain only letters and cannot be too long/empty.";
    public static final String WRONG_ZIPCODE = "Zipcode must be 5-digit number";
    public static final String WRONG_STREET_NUMBER = "Street number cannot be too long/empty.";

    //TRAIN
    public static final String WRONG_TRAIN_NAME = "Train name must have betwen 4 and 20 characters.";
    public static final String WRONG_TRAIN_TYPE = "Train type is invalid.";
    public static final String WRONG_NEGATIVE_NUMBER_TRAIN = "Train cannot have negative number of wagons/seats/rows etc.";
    public static final String WRONG_BOOLEAN_VALUE_WAGON = "Vip section and seats with table must be populated.";
    public static final String WRONG_COORDINATE = "Coordinate must be positive number.";
    public static final String WRONG_START_TIME = "Start time must be selected.";
    public static final String WRONG_LEAVING_START_TIME = "Start time must be selected(format hh:mm).";
    public static final String WRONG_DURATION_IN_MINUTES = "Duration in minutes must be positive whole number(10mins-1440mins).";
    public static final String WRONG_STATIONS = "Station departures must be added.";
    public static final String WRONG_STATION_ORDER = "Station order must be a number between 1 and 6.";
    public static final String WRONG_PRICE = "Price cannot be negative number or empty.";
    public static final String WRONG_DISCOUNT = "Discount is either 0 or greater.";
    public static final String STARTING_STATION = "Field starting station should be either true/false.";
    public static final String WRONG_STATION_DEPARTURES = "Station departures cannot be empty.";


    //TICKET
    public static final String WRONG_PASSENGERS = "There should be at least one passenger.";
    public static final String MISSING_NUM_OF_TOKENS = "Number of tokens must be greater than 0.";

}
