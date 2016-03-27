package edu.westga.rnrscramble;

import org.junit.Test;

import edu.westga.rnrscramble.controller.StringManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * StringManager tests
 *
 * Created by Ron on 3/26/2016.
 */
public class StringManagerTest {
    private static final String testString = "ABC123ABC";

    @Test
    public void testStringContainsAShouldReturnTrue() {
        StringBuilder test = new StringBuilder(testString);
        assertTrue(StringManager.remove(test, 'A'));
    }

    @Test
    public void testStringContainsMShouldReturnFalse() {
        StringBuilder test = new StringBuilder(testString);
        assertFalse(StringManager.remove(test, 'F'));
    }

    @Test
    public void whenRemoveBFromTestStringShouldReturnAC123ABC() {
        StringBuilder test = new StringBuilder(testString);
        StringManager.remove(test, 'B');
        assertEquals("AC123ABC", test.toString());
    }

    @Test
    public void whenRemoveBFromTestStringOriginalStringShouldBeUnchanged() {
        StringBuilder test = new StringBuilder(testString);
        StringManager.remove(test, 'B');
        assertEquals("ABC123ABC", testString);
    }

    @Test
    public void whenRemoveBFromTestStringTwiceShouldReturnAC123AC() {
        StringBuilder test = new StringBuilder(testString);
        StringManager.remove(test, 'B');
        StringManager.remove(test, 'B');
        assertEquals("AC123AC", test.toString());
    }

    @Test
    public void whenRemoveMFromTestStringTwiceShouldReturnOriginalString() {
        StringBuilder test = new StringBuilder(testString);
        StringManager.remove(test, 'M');
        assertEquals("ABC123ABC", test.toString());
    }

    @Test
    public void whenProcessAnswerShouldTrimScrambleUpdateAnswerAndSetInvalidCharacters() {
        StringBuilder scramble = new StringBuilder("OTAMOT");
        StringBuilder answer = new StringBuilder("PTOVMAXTM");
        StringBuilder invalid = new StringBuilder();
        StringManager.processAnswer(scramble, answer, invalid);
        // TODO: Fix processAnswer
        assertEquals("O", scramble.toString());   // TOT
        assertEquals("TOMAT", answer.toString()); // TOMATM
        assertEquals("PVXM", invalid.toString()); // PVX
    }
}
