package edu.westga.rnrscramble.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.westga.rnrscramble.R;

/**
 * File driven word generator
 *
 * Created by Ron on 4/1/2016.
 */
public class FileWordList implements IWordGenerator {
    private static String APP_TAG = "DBGTAG-FileWordList";

    private static final Random numberGenerator = new Random();
    private final Context _context;
    private final int _minLength;
    private final int _maxLength;
    private final List<String> wordList = new ArrayList<>();

    public FileWordList(Context context) throws IOException {
        _context = context;
        _minLength = 0;
        _maxLength = 0;

        // This will reference one line at a time
        String line;

        // open the word list resource
        InputStream is = _context.getResources().openRawResource(R.raw.wordlist);
        BufferedReader input = new BufferedReader(new InputStreamReader(is));

        // read the word list
        while((line = input.readLine()) != null) {
            if (!isWordValid(line)) {
                Log.e(APP_TAG, "FileWordList: Invalid word in list, '" + line + "'");
                continue;
            }
            wordList.add(line);
        }

        // Always close files.
        is.close();
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
