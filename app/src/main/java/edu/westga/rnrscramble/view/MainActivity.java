package edu.westga.rnrscramble.view;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.os.Bundle;

import android.os.PersistableBundle;
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
import edu.westga.rnrscramble.model.HintGenerator;
import edu.westga.rnrscramble.model.IWordGenerator;
import edu.westga.rnrscramble.controller.StringManager;
import edu.westga.rnrscramble.model.WordScrambler;

public class MainActivity extends AppCompatActivity {
    private static String APP_TAG = "DBGTAG-MainActivity";
    private static String SELECTED_WORD = "Selected-MainActivity";
    private static String CURRENT_GUESS = "Guess-MainActivity";

    private final TileMapManager tileMap = new TileMapManager();
    private Button btnNewWord;
    private int selectedLength = 6;
    private boolean gameOver = false;
    private String selectedWord;
    private String scrambledWord;
    private String currentAnswer = "";
    private IWordGenerator wordGenerator;
    private LinearLayout imageLayout;
    private LinearLayout answerLayout;

    private HintGenerator hintGenerator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.hintGenerator = new HintGenerator();
        try {
            wordGenerator = new FileWordList(this);
        }
        catch (Exception ex) {

        }

        this.imageLayout = (LinearLayout) findViewById(R.id.tileLinearLayout);
        this.answerLayout = (LinearLayout) findViewById(R.id.answerLayout);
        this.btnNewWord = (Button) findViewById(R.id.btnNewWord);


        if (this.imageLayout.getChildCount() == 0 && this.answerLayout.getChildCount() == 0) {
            this.NewWordClicked(this.btnNewWord);
        }
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
        this.convertAnswerString();
    }

    public void NewWordClicked(View Sender) {
        this.gameOver = false;
        this.clearLayouts();
        selectedWord = wordGenerator.nextWord(selectedLength).toUpperCase();
        scrambledWord = WordScrambler.Scramble(selectedWord);
        ImageView[] imageList = this.createTiledWord(scrambledWord);
        for (ImageView iv : imageList) {
            imageLayout.addView(iv);
        }
    }

    /**
     * Maps the characters of the scrambled word to the correct tile image and adds
     * an on click listener to each.
     * Then returns them as an array of ImageViews.
     */
    private ImageView[] createTiledWord(String tilesToCreate) {
        int count;
        int length = tilesToCreate.length();
        ImageView[] imagesViewList = new ImageView[length];
        for (count = 0; count < length; count++) {
            ImageView iv = new ImageView(this);
            iv.setImageResource(tileMap.getTile(tilesToCreate.charAt(count)));
            iv.setContentDescription(String.valueOf(tilesToCreate.charAt(count)));
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
                        MainActivity.this.convertAnswerString();
                    }
                }
            });
            imagesViewList[count] = iv;
        }
        return imagesViewList;
    }

    /**
     * Builds a string from the ImageViews in the answerLayout and checks the string against
     * that selectedString to see if the solution is correct.
     */
    private void checkAnswer() {
        this.convertAnswerString();

        StringBuilder answer = new StringBuilder(currentAnswer);
        StringManager.assureUpperCase(answer);

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

    public void hintClicked(View v) {
        if (this.currentAnswer.length() == this.selectedLength && this.gameOver) {
            Toast toast = Toast.makeText(getApplicationContext(), "You already have the correct answer", Toast.LENGTH_LONG);
            toast.show();
            return;
        }
        int hintIndex = this.hintGenerator.getHint(this.currentAnswer, this.selectedWord);
        Toast toast = Toast.makeText(getApplicationContext(), "The next letter is " + this.selectedWord.charAt(hintIndex), Toast.LENGTH_SHORT);
        toast.show();

        ImageView hintImageView = (ImageView) this.answerLayout.getChildAt(hintIndex);
        ImageView hintTile = null;
        boolean hintTileFound = false;
        while (hintImageView != null) {

            if (hintIndex >= this.answerLayout.getChildCount()) {
                hintImageView = null;
            } else {
                ImageView iv = (ImageView) this.answerLayout.getChildAt(hintIndex);


                this.answerLayout.removeView(iv);
                this.imageLayout.addView(iv);


                if(iv.getContentDescription().equals(this.selectedWord.charAt(hintIndex)) && !hintTileFound) {
                    hintTile = iv;
                    hintTileFound = true;
                }
            }
        }

        if (hintTile == null) {
            int index = 0;
            hintTile = (ImageView) this.imageLayout.getChildAt(index);
            while (!hintTile.getContentDescription().equals(String.valueOf(this.selectedWord.charAt(hintIndex)))) {
                index++;
                hintTile = (ImageView) this.imageLayout.getChildAt(index);
            }
        }

        this.imageLayout.removeView(hintTile);
        this.answerLayout.addView(hintTile);
        this.checkAnswer();
    }

    private void clearLayouts() {
        this.imageLayout.removeAllViews();
        this.answerLayout.removeAllViews();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        outState.putString(this.SELECTED_WORD, this.selectedWord);
        outState.putString(this.CURRENT_GUESS, this.currentAnswer);
    }

    private void convertAnswerString() {
        currentAnswer = "";
        int count = 0;
        while (count < this.answerLayout.getChildCount()) {
            ImageView iv = (ImageView) this.answerLayout.getChildAt(count);
            currentAnswer += iv.getContentDescription();
            count++;
        }
    }
}



