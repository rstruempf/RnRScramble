package edu.westga.rnrscramble;

import org.junit.Test;

import edu.westga.rnrscramble.model.HardCodedWordList;
import edu.westga.rnrscramble.model.WordScrambler;

import static org.junit.Assert.*;

/**
 * Unit test for the static WordScrambler class
 *
 * @author RnR
 */
public class WordScramblerTest {
    HardCodedWordList testList = new HardCodedWordList();

    /**
     * Tests to make sure that the WordScrambler returns
     * the same length word that is passed to it.
     */
    @Test
    public void shouldReturn5LetterString() {
        String testString = testList.nextWord(5);
        assertEquals(5, WordScrambler.Scramble(testString).length());
    }

    /**
     * Tests to make sure that the WordScrambler returns
     * the same length word that is passed to it.
     */
    @Test
    public void shouldReturn6LetterString() {
        String testString = testList.nextWord(6);
        assertEquals(6, WordScrambler.Scramble(testString).length());
    }
}
