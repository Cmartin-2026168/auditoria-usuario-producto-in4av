package org.camilomartin.system.utils;

public class Validations {

    public Validations() {
    }

    public Boolean validateTextFieldEmpty(String text) {
        boolean isEmpty = false;
        if (text.isEmpty() == true || text.isBlank() == true) {
            isEmpty = true;
        }
        return isEmpty;

    }

    public Boolean validateTextLength(String text, int textMax) {
        boolean isValid = false;
        if (text.length() == textMax || text.length() < textMax) {
            isValid = true;
        }
        return isValid;
    }

    public Boolean equalsText(String textOriginal, String textCompare) {
        return textOriginal.equals(textCompare);
    }

    public Boolean validateEmail(String email) {
        int dotCount = 0;
        int arrobaCount = 0;

        for (int index = 0; index < email.length(); index++) {
            if (email.charAt(index) == '.') {
                dotCount++;
            }
            if (dotCount > 1) {
                return false;
            }

        }
        for (int index = 0; index < email.length(); index++) {
            if (email.charAt(index) == '@') {
                arrobaCount++;
            }

        }

        if (arrobaCount != 1) {
            return false;

        }
        return true;
    }
}