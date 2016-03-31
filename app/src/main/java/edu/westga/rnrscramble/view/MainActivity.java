package edu.westga.rnrscramble.view;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import edu.westga.rnrscramble.R;
import edu.westga.rnrscramble.controller.TileMapManager;
import edu.westga.rnrscramble.model.HardCodedWordList;
import edu.westga.rnrscramble.model.IWordGenerator;
import edu.westga.rnrscramble.controller.StringManager;
import edu.westga.rnrscramble.model.WordScrambler;

public class MainActivity extends AppCompatActivity {
    private static String APP_TAG = "DBGTAG-MainActivity";

    private final TileMapManager tileMap = new TileMapManager();
    private int selectedLength = 6;
    private boolean gameOver = false;
    private String selectedWord;
    private String scrambledWord;
    private IWordGenerator wordGenerator = new HardCodedWordList();
    private TextView scrambleTextView;
    private EditText answerTextView;
    private LinearLayout imageLayout;
    private LinearLayout answerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        scrambleTextView = (TextView) findViewById(R.id.scramble_text);
//        answerTextView = (EditText) findViewById(R.id.answer_text);
        this.imageLayout = (LinearLayout) findViewById(R.id.tileLinearLayout);
        this.answerLayout = (LinearLayout) findViewById(R.id.answerLayout);





//        answerTextView.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                // intentionally blank
//                //Log.d(APP_TAG, "onTextChanged: s='" + s + "', start=" + start + ", before=" + before + ", count=" + count);
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence answer, int start, int count, int after) {
//                // intentionally blank
//            }
//
//            private String lastAnswer = "";
//
//            @Override
//            public void afterTextChanged(Editable answerEdit) {
//                if (gameOver) {
//                    return;
//                }
//                // if nothing has changed since the last processing, don't do anything
//                if (answerEdit.toString().equals(lastAnswer)) {
//                    return;
//                }
//                //Log.d(APP_TAG, "afterTextChanged A: answerEdit='" + answerEdit.toString() + "', " + answerTextView.getText());
//                StringBuilder answer = new StringBuilder(answerEdit);
//                StringManager.assureUpperCase(answer);
//                StringBuilder invalidCharacters = new StringBuilder();
//                StringBuilder scramble = new StringBuilder(scrambledWord);
//
//                StringManager.processAnswer(scramble, answer, invalidCharacters);
//                lastAnswer = answer.toString();
//                // update the display fields
//                scrambleTextView.setText(scramble);
//                //answerEdit.replace(0, answerEdit.length(), answer);
//                answerTextView.setText(answer);
//                answerTextView.setSelection(answer.length());
//                if (invalidCharacters.length() > 0) {
//                    String text = "Invalid character entered (" + invalidCharacters.toString() + ")";
//                    Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
//                    toast.show();
//                } else if (answer.toString().equals(selectedWord)) {
//                    gameOver = true;
//                    answerTextView.setEnabled(false);
//                    Toast toast = Toast.makeText(getApplicationContext(), "You Win!!!", Toast.LENGTH_LONG);
//                    toast.show();
//                }
//                //Log.d(APP_TAG, "afterTextChanged B: answerEdit='" + answerEdit.toString() + "', " + answerTextView.getText());
//            }
//        });

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

        return super.onOptionsItemSelected(item);
    }

    public void ClearClicked(View sender) {
        gameOver = false;
        //setScrambleTextView(scrambledWord);
        //setAnswerTextView("");
        //answerTextView.setEnabled(true);
        int count = 0;
        while (count < this.answerLayout.getChildCount()) {
            ImageView iv = (ImageView) this.answerLayout.getChildAt(count);
            this.answerLayout.removeView(iv);
            this.imageLayout.addView(iv);

        }
    }

    public void NewWordClicked(View Sender) {
        this.gameOver = false;
        this.imageLayout.removeAllViews();
        this.answerLayout.removeAllViews();
        selectedWord = wordGenerator.nextWord(selectedLength).toUpperCase();
        scrambledWord = WordScrambler.Scramble(selectedWord);
        this.createTiledWord();
    }

    private void setScrambleTextView(String word) {
        scrambleTextView.setText(word.toUpperCase());
    }

    private void setAnswerTextView(String word) {
        answerTextView.setText(word.toUpperCase());
    }


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
}



