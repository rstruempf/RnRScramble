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
        assertTrue(StringManager.contains(testString, 'A'));
    }

    @Test
    public void testStringContainsMShouldReturnFalse() {
        assertFalse(StringManager.contains(testString, 'F'));
    }

    @Test
    public void whenRemoveBFromTestStringShouldReturnAC123ABC() {
        assertEquals("AC123ABC", StringManager.remove(testString, 'B'));
    }

    @Test
    public void whenRemoveBFromTestStringOriginalStringShouldBeUnchanged() {
        StringManager.remove(testString, 'B');
        assertEquals("ABC123ABC", testString);
    }

    @Test
    public void whenRemoveBFromTestStringTwiceShouldReturnAC123AC() {
        assertEquals("AC123AC", StringManager.remove(StringManager.remove(testString, 'B'), 'B'));
    }

    @Test
    public void whenRemoveMFromTestStringTwiceShouldReturnOriginalString() {
        assertEquals("ABC123ABC", StringManager.remove(testString, 'M'));
    }

}
