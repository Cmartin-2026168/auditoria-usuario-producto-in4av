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
        int arrobaCount = 0;
        int arrobaIndex = -1;

        // Contar cuántas arrobas tiene y en qué posición está
        for (int index = 0; index < email.length(); index++) {
            if (email.charAt(index) == '@') {
                arrobaCount++;
                arrobaIndex = index;
            }
        }

        // Debe haber exactamente una arroba
        if (arrobaCount != 1) {
            return false;
        }

        // La arroba no puede estar al inicio ni al final (ej: "@correo" o "correo@")
        if (arrobaIndex == 0 || arrobaIndex == email.length() - 1) {
            return false;
        }

        // Buscar si hay al menos un punto DESPUÉS de la arroba (el dominio)
        String dominio = email.substring(arrobaIndex + 1);
        boolean tienePunto = false;
        for (int index = 0; index < dominio.length(); index++) {
            if (dominio.charAt(index) == '.') {
                tienePunto = true;
            }
        }

        if (tienePunto == false) {
            return false;
        }

        return true;
    }
}
