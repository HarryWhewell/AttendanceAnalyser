package com.brightfuture.application.util;

/** Created by dbarczak on 19/06/2015. */
public class ValidationConstants {

    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static final String PASSWORD_PATTERN = "^[a-zA-Z0-9]{6,40}$";

    public static final String FULL_NAME_PATTERN = "^[a-zA-Z]{2,30}$";

    public static final String POST_CODE = "^(GIR ?0AA|[A-PR-UWYZ]([0-9]{1,2}|([A-HK-Y][0-9]" +
            "([0-9ABEHMNPRV-Y])?)|[0-9][A-HJKPS-UW]) ?[0-9][ABD-HJLNP-UW-Z]{2})$";
}
