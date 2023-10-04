package com.ftn.railwayapp.util;


import java.text.SimpleDateFormat;

public class Constants {

    public static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");

    //PATHS
    public static final String QR_CODE_PACKAGE_PATH = "./src/main/resources/static/qrCode/";
    public static final String TEMPLATE_FILE_PATH = "./src/main/resources/static/templates/";
    public static final String THYMLEAF_PDF_TEMPLATE_FILE_PATH = "pdf-templates/PDFTemplate";
    public static final String PDF_OUT_FILE_PATH = "./src/main/resources/pdf-templates/pdf/";

    //REGEX
    public static final String LEGIT_PASSWORD_REG = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{6,50}$";
    public static final String LEGIT_FULL_NAME_REG = "^[A-Za-z ]{2,50}$";
    public static final String VERIFICATION_CODE_REG = "^\\d{4}$";
    public static final String LEGIT_CITY_AND_STREET_REG = "[a-zA-Z ]{1,20}";
    public static final String POSITIVE_WHOLE_NUMBER_REG = "[1-9][0-9]*";
    public static final String ZIPCODE_REG = "^\\d{5}$";
    public static final String NOT_WHOLE_NUMBER_REG = "^[0-9.]+$";
    public static final String TIME_PATTERN = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";


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

    public static final String TRANSACTION_DESCRIPTION = "Buying tokens for SerbianRailways";
    public static final String PAYMENT_METHOD = "paypal";
    public static final String PAYMENT_INTENT = "sale";
    public static final String REDIRECT_URL_CANCEL = "http://localhost:4200/railway-system/regular/payment/status/-1";
    public static final String REDIRECT_URL_SUCCESS = "http://localhost:4200/railway-system/regular/payment/process-payment";
    public static final String PAYPAL_APPROVAL_URL = "approval_url";

    public static final int QR_CODE_HEIGHT = 120;
    public static final int QR_CODE_WIDTH = 120;

    public static String getQRCodePath(String id) {
        return String.format("http://localhost:8080/ticket/board/%s", id);
    }

}
