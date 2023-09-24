package com.ftn.railwayapp.util;

import java.util.Random;


public class Constants {

    private static final Random random = new Random();

    //REGEX
    public static final String LEGIT_PASSWORD_REG = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{6,50}$";


    //CONSTANTS
    public static final String NOT_PROVIDED_STRING = "NOT_PROVIDED";
    public static final double MIN_BALANCE_ACC_NUM = 1000000000000.0;
    public static final double MAX_BALANCE_ACC_NUM = 9999999999999.0;

    public static final String ROLE_REGULAR = "ROLE_REGULAR";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    //METHODS
    public static String generateSecurityCode() {
        return String.valueOf((long) (Math.pow(10, 12) + random.nextDouble() * Math.pow(10, 12)));
    }

}
