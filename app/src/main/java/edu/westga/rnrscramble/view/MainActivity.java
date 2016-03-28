package edu.westga.rnrscramble.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.westga.rnrscramble.R;
import edu.westga.rnrscramble.model.HardCodedWordList;
import edu.westga.rnrscramble.model.IWordGenerator;
import edu.westga.rnrscramble.controller.StringManager;
import edu.westga.rnrscramble.model.WordScrambler;

public class MainActivity extends AppCompatActivity {
    private static String APP_TAG = "DBGTAG-MainActivity";

    int selectedLength = 6;
    String selectedWord;
    String scrambledWord;
    IWordGenerator wordGenerator = new HardCodedWordList();
    TextView scrambleTextView;
    EditText answerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        scrambleTextView = (TextView) findViewById(R.id.scramble_text);
        answerTextView = (EditText) findViewById(R.id.answer_text);
        answerTextView.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // intentionally blank
                //Log.d(APP_TAG, "onTextChanged: s='" + s + "', start=" + start + ", before=" + before + ", count=" + count);
            }

            @Override
            public void beforeTextChanged(CharSequence answer, int start, int count, int after) {
                // intentionally blank
            }

            private String lastAnswer = "";

            @Override
            public void afterTextChanged(Editable answerEdit) {
                // if nothing has changed since the last processing, don't do anything
                if (answerEdit.toString().equals(lastAnswer)) {
                    return;
                }
                //Log.d(APP_TAG, "afterTextChanged A: answerEdit='" + answerEdit.toString() + "', " + answerTextView.getText());
                StringBuilder answer = new StringBuilder(answerEdit);
                StringManager.assureUpperCase(answer);
                StringBuilder invalidCharacters = new StringBuilder();
                StringBuilder scramble = new StringBuilder(scrambledWord);

                StringManager.processAnswer(scramble, answer, invalidCharacters);
                lastAnswer = answer.toString();
                // update the display fields
                scrambleTextView.setText(scramble);
                //answerEdit.replace(0, answerEdit.length(), answer);
                answerTextView.setText(answer);
                answerTextView.setSelection(answer.length());
                if (invalidCharacters.length() > 0) {
                    String text = "Invalid character entered (" + invalidCharacters.toString() + ")";
                    Toast toast = Toast.makeText( getApplicationContext(), text, Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if (answer.toString().equals(selectedWord)) {
                    // TODO: Set flag saying Done, don't process or allow additional input until clear or new word
                    Toast toast = Toast.makeText( getApplicationContext(), "You Win!!!", Toast.LENGTH_LONG);
                    toast.show();
                }
                //Log.d(APP_TAG, "afterTextChanged B: answerEdit='" + answerEdit.toString() + "', " + answerTextView.getText());
            }
        });

        NewWordClicked(null);
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
        setScrambleTextView(scrambledWord);
        setAnswerTextView("");
    }

    public void NewWordClicked(View Sender) {
        selectedWord = wordGenerator.nextWord(selectedLength).toUpperCase();
        scrambledWord = WordScrambler.Scramble(selectedWord);
        setScrambleTextView(scrambledWord);
        setAnswerTextView("");
    }

    private void setScrambleTextView(String word) {
        scrambleTextView.setText(word.toUpperCase());
    }

    private void setAnswerTextView(String word) {
        answerTextView.setText(word.toUpperCase());
    }
}
