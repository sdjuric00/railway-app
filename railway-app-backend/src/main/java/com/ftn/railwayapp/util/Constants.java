package com.ftn.railwayapp.util;


public class Constants {

    //PATHS
    public static final String TEMPLATE_FILE_PATH = "./src/main/resources/static/templates/";

    //REGEX
    public static final String LEGIT_PASSWORD_REG = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{6,50}$";
    public static final String LEGIT_FULL_NAME_REG = "^[A-Za-z ]{2,50}$";
    public static final String VERIFICATION_CODE_REG = "^\\d{4}$";
    public static final String LEGIT_CITY_AND_STREET_REG = "[a-zA-Z ]{1,20}";
    public static final String POSITIVE_WHOLE_NUMBER_REG = "[1-9][0-9]*";
    public static final String ZIPCODE_REG = "^\\d{5}$";

    //CONSTANTS
    public static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
    public static final String NOT_PROVIDED_STRING = "NOT_PROVIDED";
    public static final String ROLE_REGULAR = "ROLE_REGULAR";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String WRONG_SECURITY_CODE = "Security code is number greater than 0.";
    public static final String WRONG_VERIFY_CODE = "Security verify id.";



    public static final int MAX_NUM_VERIFY_TRIES = 3;
    public static final int SALT_LENGTH = 4;
    public static final int MIN_SECURITY_NUM = 1000;
    public static final int MAX_SECURITY_NUM = 9999;
    public static final int ZERO_FAILED_ATTEMPTS = 0;

}
