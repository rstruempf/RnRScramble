package edu.westga.rnrscramble.view;

import android.app.ActionBar;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import edu.westga.rnrscramble.R;
import edu.westga.rnrscramble.controller.TileMapManager;
import edu.westga.rnrscramble.model.FileWordList;
import edu.westga.rnrscramble.model.IWordGenerator;
import edu.westga.rnrscramble.controller.StringManager;
import edu.westga.rnrscramble.model.WordScrambler;

public class MainActivity extends AppCompatActivity {
    private static String APP_TAG = "DBGTAG-MainActivity";

    private final TileMapManager tileMap = new TileMapManager();
    private Button btnNewWord;
    private int selectedLength = 6;
    private boolean gameOver = false;
    private String selectedWord;
    private String scrambledWord;
    private IWordGenerator wordGenerator;
    private LinearLayout imageLayout;
    private LinearLayout answerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            wordGenerator = new FileWordList(this);
        }
        catch (Exception ex) {

        }
        this.imageLayout = (LinearLayout) findViewById(R.id.tileLinearLayout);
        this.answerLayout = (LinearLayout) findViewById(R.id.answerLayout);
        this.btnNewWord = (Button) findViewById(R.id.btnNewWord);
        this.NewWordClicked(this.btnNewWord);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.five_letter_words) {
            if (this.selectedLength == 6) {
                this.selectedLength = 5;
                this.clearLayouts();
                this.NewWordClicked(this.btnNewWord);
            }
        }
        if (id== R.id.six_letter_words) {
            if (this.selectedLength == 5) {
                this.selectedLength = 6;
                this.clearLayouts();
                this.NewWordClicked(this.btnNewWord);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void ClearClicked(View sender) {
        gameOver = false;
        int count = 0;
        while (count < this.answerLayout.getChildCount()) {
            ImageView iv = (ImageView) this.answerLayout.getChildAt(count);
            this.answerLayout.removeView(iv);
            this.imageLayout.addView(iv);
        }
    }

    public void NewWordClicked(View Sender) {
        this.gameOver = false;
        this.clearLayouts();
        selectedWord = wordGenerator.nextWord(selectedLength).toUpperCase();
        scrambledWord = WordScrambler.Scramble(selectedWord);
        this.createTiledWord();
    }

    /**
     * Maps the characters of the scrambled word to the correct tile image and adds
     * an on click listener to each.
     */
    private void createTiledWord() {
        int count;

        for (count = 0; count < this.selectedLength; count++) {
            ImageView iv = new ImageView(this);
            iv.setImageResource(tileMap.getTile(scrambledWord.charAt(count)));
            iv.setContentDescription(String.valueOf(scrambledWord.charAt(count)));
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View sender) {
                    ImageView tile = (ImageView) sender;
                    if (tile.getParent() == MainActivity.this.imageLayout) {
                        MainActivity.this.imageLayout.removeView(tile);
                        MainActivity.this.answerLayout.addView(tile);
                        MainActivity.this.checkAnswer();

                    } else {
                        MainActivity.this.answerLayout.removeView(tile);
                        MainActivity.this.imageLayout.addView(tile);
                    }
                }
            });

            this.imageLayout.addView(iv);
        }
    }

    /**
     * Builds a string from the ImageViews in the answerLayout and checks the string against
     * that selectedString to see if the solution is correct.
     */
    private void checkAnswer() {
        String currentAnswer = "";
        int count = 0;
        while (count < this.answerLayout.getChildCount()) {
            ImageView iv = (ImageView) this.answerLayout.getChildAt(count);
            currentAnswer += iv.getContentDescription();
            count++;
        }

        StringBuilder answer = new StringBuilder(currentAnswer);
        StringManager.assureUpperCase(answer);
        StringBuilder invalidCharacters = new StringBuilder();
        StringBuilder scramble = new StringBuilder(scrambledWord);

        StringManager.processAnswer(scramble, answer, invalidCharacters);

        if (answer.toString().equals(selectedWord)) {
            gameOver = true;
            Toast toast = Toast.makeText(getApplicationContext(), "You Win!!!", Toast.LENGTH_LONG);
            toast.show();
        }

        if (currentAnswer.length() == this.selectedLength && !gameOver) {
            Toast toast = Toast.makeText(getApplicationContext(), "Sorry! Wrong Answer!", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private void clearLayouts() {
        this.imageLayout.removeAllViews();
        this.answerLayout.removeAllViews();
    }
}



