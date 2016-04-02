package edu.westga.rnrscramble;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.westga.rnrscramble.model.FileWordList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for file driven word list
 *
 * Created by Ron on 4/1/2016.
 */
public class FileWordListTest {
    private final List<String> wordList = new ArrayList<String>(
            Arrays.asList("One", "Two", "Three", "Go", "Banshee"));

    @Test
    public void whenValidateNoWordShouldReturnFalse() {
        assertFalse(FileWordList.isWordValid(""));
    }

    @Test
    public void whenValidateTESTShouldReturnTrue() {
        assertTrue(FileWordList.isWordValid("test"));
    }

    @Test
    public void whenValidateWordWithSpaceShouldReturnFalse() {
        assertFalse(FileWordList.isWordValid("test word"));
    }

    @Test
    public void whenValidateWordWithDigitsShouldReturnFalse() {
        assertFalse(FileWordList.isWordValid("test1"));
    }

    @Test
    public void whenValidateLongWordShouldReturnFalse() {
        assertFalse(FileWordList.isWordValid("testaliciousfergilicioussupercaliwordamiscioussuperduperwordtoolongtest"));
    }

    @Test
    public void whenGetWord0ShouldReturnAWord() {
        String word = FileWordList.getWord(0, wordList);
        assertNotEquals("", word);
    }

    @Test
    public void whenGetWord7ShouldReturnBanshee() {
        String word = FileWordList.getWord(7, wordList);
        assertEquals("Banshee", word);
    }

    @Test
    public void whenGetWord2ShouldReturnGo() {
        String word = FileWordList.getWord(2, wordList);
        assertEquals("Go", word);
    }

    @Test
    public void whenGetWord10ShouldReturnNoWord() {
        String word = FileWordList.getWord(10, wordList);
        assertEquals("", word);
    }

}
