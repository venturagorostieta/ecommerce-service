package com.tech.trove.infrastructure.common.constant;

import lombok.experimental.UtilityClass;

/**
 * The type Tech trove core constant.
 */
@UtilityClass
public class TechTroveCoreConstant {

    /**
     * The constant AUTHORIZATION.
     */
    public static final String AUTHORIZATION = "Authorization";

    /**
     * The constant BEARER.
     */
    public static final String BEARER = "Bearer ";

    /**
     * The constant X_API_KEY.
     */
    public static final String X_API_KEY = "x-api-key";

    /**
     * The constant COLON.
     */
    public static final String COLON = ":";

    /**
     * The constant COMMA_SPACE.
     */
    public static final String COMMA_SPACE = ", ";

    /**
     * domain position in array domain:operation:action
     */
    public static final int DOMAIN_POSITION = 0;

    /**
     * operation position in array domain:operation:action
     */
    public static final int OPERATION_POSITION = 1;

    /**
     * action position in array domain:operation:action
     */
    public static final int ACTION_POSITION = 2;

    /**
     * The constant SLASH.
     */
    public static final String SLASH = "/";

    /**
     * The constant REGEX_LEFT_BRACKET.
     */
    public static final String REGEX_LEFT_BRACKET = "\\[";

    /**
     * The constant REGEX_RIGHT_BRACKET.
     */
    public static final String REGEX_RIGHT_BRACKET = "\\]";

    /**
     * The constant CONCAT_OK.
     */
    public static final String CONCAT_OK = " ok";

    /**
     * The constant ERROR_WHILE_FOR.
     */
    public static final String ERROR_WHILE_FOR = "Error while ... {} for {}";

    /**
     * The constant REGEX_EMAIL.
     */
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9]{1}[a-zA-Z0-9\\._\\-]+[a-zA-Z0-9]+@[a-zA-Z0-9]{1}[a-zA-Z0-9\\._\\-]+\\.[a-z]+$";

    /**
     * The constant REGEX_FIRST_NAME.
     */
    public static final String REGEX_FIRST_NAME = "^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]{1,19}(([ ][A-ZÁÉÍÓÚÑ][a-záéíóúñ]{1,19})*){3,21}$";

    /**
     * The constant REGEX_LAST_NAME.
     */
    public static final String REGEX_LAST_NAME = "^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]{1,19}(([ ][A-ZÁÉÍÓÚÑ][a-záéíóúñ]{1,19})*){3,21}$";

    /**
     * The constant REGEX_USERNAME.
     */
    public static final String REGEX_USERNAME = "^[0-9a-z]{12,55}$";

    /**
     * The constant REGEX_PASSWORD.
     */
    public static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[#?!@$ %^&*\\-_]).{8,30}$";

    /**
     * The constant REGEX_VERIFICATION_CODE.
     */
    public static final String REGEX_VERIFICATION_CODE = "^((?=.*[A-Z])|(?=.*[a-z])|(?=.*[0-9])|(?=.*[#?!@$ %^&*\\-_])).{1,30}$";

    /**
     * The constant EMAIL_INVALID_CODE_907.
     */
    public static final String EMAIL_INVALID_CODE_907 = "907|The 'email' field is not valid";

    /**
     * The constant PASSWORD_INVALID_CODE_911.
     */
    public static final String PASSWORD_INVALID_CODE_911 = "908|The 'password' field is not valid, it requires minimum eight characters, at least one upper case English letter, one lower case English letter, one number and one special character";

    /**
     * The constant VERIFICATION_CODE_INVALID_CODE_912.
     */
    public static final String VERIFICATION_CODE_INVALID_CODE_912 = "912|The 'verification code' field is not valid, it requires minimum one character, at least one upper case English letter or one lower case English letter or one number or one special character";

    /**
     * maximum elements in array of db record
     */
    public static final int NOTIFICATION_STAT_MAX_SIZE = 5;

    /**
     * maximum records of OTP in a day
     */
    public static final int NOTIFICATION_OTP_LIMIT_IN_A_DAY = 3;

    /**
     * The constant LIMIT_ONE_DAY.
     */
    public static final long LIMIT_ONE_DAY = 1;

    /**
     * maximum elements in array of db record
     */
    public static final int NOTIFICATION_OTP_MAX_SIZE = 5;

    /**
     * The constant ZERO.
     */
    public static final int ZERO = 0;

    /**
     * The constant ONE.
     */
    public static final int ONE = 1;

}
