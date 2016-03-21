package edu.westga.rnrscramble;

import org.junit.Test;
import static org.junit.Assert.*;
import edu.westga.rnrscramble.model.HardCodedWordList;

/**
 * JUnit test case for the HardCodedWordList class
 *
 * @author RnR
 */
public class HardCodedWordListTest {
    private HardCodedWordList testList = new HardCodedWordList();


    @Test
    public void shouldReturnAFiveLetterWord() {
        String fiveLetterWord = testList.nextWord(5);
        assertEquals(5, fiveLetterWord.length());
    }

    @Test
    public void shouldReturnASixLetterWord() {
        String sixLetterWord = testList.nextWord(6);
        assertEquals(6, sixLetterWord.length());
    }
}
