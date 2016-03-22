package edu.westga.rnrscramble.model;

/**
 * Interface for the word generator. This interface has one method that accepts
 * an integer and returns a word as a string of that length;
 *
 * @author RnR
 */
public interface IWordGenerator {

    /**
     * Returns a string of the desired length
     * @param length - length of the string to return
     * @return - A word as a string.
     */
    String nextWord(int length);
}
