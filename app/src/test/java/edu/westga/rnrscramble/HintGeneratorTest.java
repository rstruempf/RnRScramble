package edu.westga.rnrscramble;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import edu.westga.rnrscramble.model.HintGenerator;

/**
 * Unit test case for the HintGenerator class.
 *
 * Created by Ryan on 4/3/2016.
 */
public class HintGeneratorTest
{
    HintGenerator testGenerator = new HintGenerator();
    String fullWord = "ANIMAL";
    
    @Test
    public void ShouldReturnAForHintIfNoGuess() {
        assertEquals(new Character('A'), this.testGenerator.getHint("", fullWord));
    }

    @Test
    public void ShouldReturnAForHintIfWrongGuess() {
        assertEquals(new Character('A'), this.testGenerator.getHint("B", fullWord));
    }

    @Test
    public void ShouldReturnAForHintIfWrongGuessWithMultipleCharacters() {
        assertEquals(new Character('A'), this.testGenerator.getHint("Cow", fullWord));
    }

    @Test
    public void ShouldReturnMForHintIfWrongGuessWithSomeCorrectCharacters() {
        assertEquals(new Character('M'), this.testGenerator.getHint("ANIPJF", fullWord));
    }
}
