package com.zyneonstudios.zetcore.api;

public class StringAPI {

    public boolean isNumericPart(String check) {
        if (check == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(check);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public boolean isNumeric(String check) {
        if(isNumericPart(check)) {
            return !(Double.parseDouble(check) > 999999998);
        } else {
            return false;
        }
    }
}