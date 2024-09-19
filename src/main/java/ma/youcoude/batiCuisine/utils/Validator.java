package ma.youcoude.batiCuisine.utils;

import java.util.regex.Pattern;

public class Validator {

    public static boolean validatePhoneNumber(String phoneNumber) {
        Pattern VALID_PHONE_NUMBER_REGEX = Pattern.compile("^(05|06|07)\\d{8}$");
        return VALID_PHONE_NUMBER_REGEX.matcher(phoneNumber).matches();
    }
}
