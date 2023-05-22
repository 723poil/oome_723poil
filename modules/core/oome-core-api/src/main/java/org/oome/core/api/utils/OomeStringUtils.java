package org.oome.core.api.utils;

import org.apache.commons.lang3.ObjectUtils;

public class OomeStringUtils {

    public static String convertToCamelCase(String underbarStr) {
        if (ObjectUtils.isEmpty(underbarStr)) {
            return underbarStr;
        }

        StringBuilder camelCase = new StringBuilder();
        boolean capitalizeNext = false;

        for (int i = 0; i < underbarStr.length(); i++) {
            char currentChar = underbarStr.charAt(i);

            if (currentChar == ' ' || currentChar == '_' || currentChar == '-') {
                capitalizeNext = true;
            } else if (capitalizeNext) {
                camelCase.append(Character.toUpperCase(currentChar));
                capitalizeNext = false;
            } else {
                camelCase.append(Character.toLowerCase(currentChar));
            }
        }

        return camelCase.toString();
    }
}
