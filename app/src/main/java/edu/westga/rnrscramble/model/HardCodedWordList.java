package edu.westga.rnrscramble.model;

import java.util.Random;

/**
 * Class that contains two lists of hard-coded words that are
 * 5 and 6 letters in length.
 *
 * @author RnR
 */
public class HardCodedWordList implements IWordGenerator {
    private final String[] fiveLetterWordList = new String[] {"grape", "flash", "phone", "viral", "wrath"};
    private final String[] sixLetterWordList = new String[] {"season", "turtle", "guitar", "health", "tomato"};
    private final Random numberGenerator = new Random();

    @Override
    public int minLength() {
        return 5;
    }

    @Override
    public int maxLength() {
        return 6;
    }

    @Override
    public String nextWord(int length) {
        String theWord = "";

        if (length == 0) {
            length = minLength() + numberGenerator.nextInt(maxLength() - minLength()) + 1;
        }
        if (length == 5) {
            theWord = fiveLetterWordList[numberGenerator.nextInt(fiveLetterWordList.length)];
        } else if (length == 6) {
            theWord = sixLetterWordList[numberGenerator.nextInt(sixLetterWordList.length)];
        } else if (length < 5) {
            throw new IllegalArgumentException("Length must be 5 or 6");
        } else if (length > 6) {
            throw new IllegalArgumentException("Length must be 5 or 6");
        }
        return theWord;
    }

}
