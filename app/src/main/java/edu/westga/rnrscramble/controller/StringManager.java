package edu.westga.rnrscramble.controller;

import android.text.Editable;

/**
 * Utility code to make changes to character strings
 *
 * Created by Ron on 3/25/2016.
 */
public class StringManager {
    /**
     * Remove the first occurrence, if any, of a given character from a string
     *
     * @param str String to modify
     * @param chr Character to remove
     * @return True if the letter was found and removed
     */
    public static boolean remove(StringBuilder str, char chr)
    {
        for (int idx = 0; idx < str.length(); idx++ ) {
            if (str.charAt(idx) != chr) {
                continue;
            }
            str.delete(idx, idx+1);
            return true;
        }
        return false;
    }

    /**
     * Convert any lowercase characters in an Editable string to uppercase
     *
     * @param str StringBuffer to process
     */
    public static void assureUpperCase(StringBuilder str) {
        for (int idx = 0; idx < str.length(); idx++) {
            char chr = str.charAt(idx);
            if (!Character.isUpperCase(chr)) {
                str.replace(idx, idx+1, String.valueOf(Character.toUpperCase(chr)));
            }
        }
    }

    /**
     * For a given scramble answer entry, determine what answer should look like, letters remaining, and invalid characters
     *
     * @param scramble Scrambled word
     * @param answer Current answer
     * @param invalidCharacters Invalid characters that were removed from the answer
     */
    public static void processAnswer(StringBuilder scramble, StringBuilder answer, StringBuilder invalidCharacters) {
        for (int idx = 0; idx < answer.length(); idx++) {
            char chr = answer.charAt(idx);
            if (!remove(scramble, chr)) {
                invalidCharacters.append(chr);
                answer.delete(idx, idx+1);
            }
        }
    }
}
