package edu.westga.rnrscramble.model;

import java.util.IllegalFormatCodePointException;

/**
 * Compares a string to another string and returns the
 * first character that is different in the solution string.
 *
 * Created by Ryan on 4/3/2016.
 */
public class HintGenerator {

    public Character getHint(String guessString, String fullWord) {
        Character hintCharacter = null;
        if (fullWord.equals(null)) {
            throw new IllegalArgumentException("You must enter a word to check");
        }
        if (guessString.equals(null)) {
            throw new IllegalArgumentException("Solution string cannot be null");
        }

        if (guessString.equals("")) {
            hintCharacter = fullWord.charAt(0);
        }
        else
        {
            char[] guess = guessString.toCharArray();
            char[] word = fullWord.toCharArray();
            int index = 0;

            while (guess[index] == word[index]){
                index++;
            }

            hintCharacter = word[index];
        }

        return hintCharacter;
    }



}
