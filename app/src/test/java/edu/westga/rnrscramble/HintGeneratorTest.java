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
    public void ShouldReturn0ForHintIfNoGuess() {
        assertEquals(0, this.testGenerator.getHint("", fullWord));
    }

    @Test
    public void ShouldReturn0ForHintIfWrongGuess() {
        assertEquals(0, this.testGenerator.getHint("B", fullWord));
    }

    @Test
    public void ShouldReturn0ForHintIfWrongGuessWithMultipleCharacters() {
        assertEquals(0, this.testGenerator.getHint("Cow", fullWord));
    }

    @Test
    public void ShouldReturn3ForHintIfWrongGuessWithSomeCorrectCharacters() {
        assertEquals(3, this.testGenerator.getHint("ANIPJF", fullWord));
    }

    @Test
    public void ShouldReturn2ForHintWhenItIsTheNextLetter() {
        assertEquals(2, this.testGenerator.getHint("AN", fullWord));
    }
}
