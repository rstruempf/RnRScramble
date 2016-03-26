package edu.westga.rnrscramble.controller;

import android.text.Editable;

/**
 * Utility code to make changes to character strings
 *
 * Created by Ron on 3/25/2016.
 */
public class StringManager {
    /**
     * See whether a string contains a given character
     *
     * @param str String to search
     * @param chr Character to search for
     * @return True if the string contains the given character
     */
    public static boolean contains(String str, char chr) {
        return (str.indexOf(chr) >= 0);
    }

    /**
     * Remove the first occurrence, if any, of a given character from a string
     *
     * @param str String to modify
     * @param chr Character to remove
     * @return Modified string
     */
    public static String remove(String str, char chr)
    {
        int idx = str.indexOf(chr);

        if (idx < 0) {
            return str;
        }
        String firstPart = "";
        String secondPart = "";
        if (idx != 0) {
            firstPart = str.substring(0, idx);
        }
        if (idx != str.length() - 1) {
            secondPart = str.substring(idx+1, str.length());
        }
        return firstPart + secondPart;
    }

    /**
     * Convert any lowercase characters in an Editable string to uppercase
     *
     * @param str Editable string to process
     */
    public static void assureUpperCase(Editable str) {
        for (int idx = 0; idx < str.length(); idx++) {
            char chr = str.charAt(idx);
            if (!Character.isUpperCase(chr)) {
                str.replace(idx, idx+1, String.valueOf(Character.toUpperCase(chr)));
            }
        }
    }
}
