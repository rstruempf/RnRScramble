package edu.westga.rnrscramble.model;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/**
 * File driven word generator
 *
 * Created by Ron on 4/1/2016.
 */
public class FileWordList implements IWordGenerator {
    private static String APP_TAG = "DBGTAG-FileWordList";

    private static final Random numberGenerator = new Random();
    private final int _minLength;
    private final int _maxLength;
    private final List<String> wordList = new ArrayList<>();

    public FileWordList() {
        _minLength = 0;
        _maxLength = 0;
        // TODO: Load word list
        // TODO: Set min/max length
    }

    @Override
    public int minLength() {
        return _minLength;
    }

    @Override
    public int maxLength() {
        return _maxLength;
    }

    @Override
    public String nextWord(int length) {
        if (length != 0 && (length < minLength() || length > maxLength())) {
            throw new IllegalArgumentException("Length must be between " + minLength() + " and " + maxLength());
        }
        return getWord(length, wordList);
    }

    /**
     * Word validator
     *
     * @param theWord Word to test
     * @return True if word is a valid scramble word
     */
    public static boolean isWordValid(String theWord) {
        if (theWord == null || theWord.length() == 0) {
            return false;
        }
        if (theWord.length() > MAX_WORD_LENGTH) {
            return false;
        }
        for (int idx = 0; idx < theWord.length(); idx++) {
            char chr = theWord.charAt(idx);
            if (!Character.isLetter(chr)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Utility function to select a word
     *
     * @param length Desired word length
     * @param wordList Word list
     * @return Selected word
     */
    public static String getWord(int length, List<String> wordList) {
        int count;
        // count number of words of the desired length
        if (length == 0) {
            count = wordList.size();
        }
        else {
            count = 0;
            for (String word : wordList) {
                if (word.length() == length) {
                    count++;
                }
            }
        }
        // if no words of selected length, return empty string
        if (count == 0) {
            return "";
        }
        // select word to return
        int wordIndex = numberGenerator.nextInt(count);
        // return selected word
        if (length == 0) {
            return wordList.get(wordIndex);
        }
        else {
            count = 0;
            for (String word : wordList) {
                if (word.length() != length) {
                    continue;
                }
                if (wordIndex == 0) {
                    return word;
                }
                wordIndex--;
            }
        }
        // should never happen
        return "";
    }

}
