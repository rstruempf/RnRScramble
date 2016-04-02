package edu.westga.rnrscramble;

import org.junit.Test;

import edu.westga.rnrscramble.model.FileWordList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for file driven word list
 *
 * Created by Ron on 4/1/2016.
 */
public class FileWordListTest {

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

    // TODO: Test FileWordList.getWord(length, wordList)
    @Test
    public void whenGetWord0ShouldReturnAWord() {
        // TODO: replace
        assertEquals(5, 4);
    }

}
