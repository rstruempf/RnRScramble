package edu.westga.rnrscramble.model;

/**
 * Interface for the word generator. This interface has one method that accepts
 * an integer and returns a word as a string of that length;
 *
 * @author RnR
 */
public interface IWordGenerator {

    /**
     * Minimum word length available from generator
     *
     * @return Min word length available
     */
    int minLength();

    /**
     * Maximum word length available from generator
     *
     * @return Max word length available
     */
    int maxLength();

    /**
     * Returns a string of the desired length
     *
     * @param length - length of the string to return (0 for random)
     * @return - A word as a string.
     */
    String nextWord(int length);
}
