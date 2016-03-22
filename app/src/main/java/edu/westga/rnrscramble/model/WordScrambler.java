package edu.westga.rnrscramble.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class that randomly rearranges the characters in a string.
 *
 * @author RnR
 */
public class WordScrambler {

    /**
     * Accepts a string, rearranges its characters and returns the rearranged string.
     * @param wordToScramble - the string to rearrange
     * @return scrambledWord - the rearranged String
     */
    public static String Scramble(String wordToScramble) {
        String scrambledWord = "";
        ArrayList<Character> wordArray = WordScrambler.convertToArrayList(wordToScramble);
        int length = wordArray.size();
        int index;
        Random generator = new Random();

        while (length > 0) {
            index = generator.nextInt(wordArray.size());
            scrambledWord += wordArray.get(index);
            wordArray.remove(index);
            length = wordArray.size();
        }
        return scrambledWord;
    }

    /**
     * Converts the string to an array of characters.
     * @param word - the string to convert to an ArrayList
     * @return wordCharacterList - ArrayList of characters from the string.
     */
    private static ArrayList<Character> convertToArrayList(String word) {
        ArrayList<Character> wordCharacterList = new ArrayList<>();
        for (char wordCharacter : word.toCharArray()) {
            wordCharacterList.add(wordCharacter);
        }
        return wordCharacterList;
    }
}
