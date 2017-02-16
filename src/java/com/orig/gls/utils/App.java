package com.orig.gls.utils;

public class App {

    public static final String EMPTY_STRING = "";
    public static final String LOGGER = "origlogger";
    public static final String DATEFORMAT = "dd-MMM-yyyy";

    public static boolean isAdd(String function) {
        return function.equalsIgnoreCase("ADD");
    }

    public static boolean isVerify(String function) {
        return function.equalsIgnoreCase("VERIFY");
    }

    public static boolean isModify(String function) {
        return function.equalsIgnoreCase("MODIFY");
    }

    public static boolean isDelete(String function) {
        return function.equalsIgnoreCase("DELETE");
    }

    public static boolean isCancel(String function) {
        return function.equalsIgnoreCase("CANCEL");
    }

    public static boolean isInquire(String function) {
        return function.equalsIgnoreCase("INQUIRE");
    }

}
