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
                Log.d(APP_TAG, "onTextChanged: s='" + s + "', start=" + start + ", before=" + before + ", count=" + count);
            }

            @Override
            public void beforeTextChanged(CharSequence answer, int start, int count, int after) {
                // intentionally blank
            }

            @Override
            public void afterTextChanged(Editable answer) {
                StringBuilder invalidCharacters = new StringBuilder();
                StringManager.assureUpperCase(answer);
                Log.d(APP_TAG, "afterTextChanged: answer='" + answer.toString() + "', ");
                // Remove all letters from scramble text that have been used
                String scramble = scrambledWord;
                for (int idx = 0; idx < answer.length(); idx++) {
                    char chr = answer.charAt(idx);
                    if (StringManager.contains(scramble, chr)) {
                        scramble = StringManager.remove(scramble, chr);
                    }
                    else {
                        invalidCharacters.append(chr);
                        answer.delete(idx, idx+1);
                    }
                }
                // set scramble to letters that have not been used
                scrambleTextView.setText(scramble);
                if (invalidCharacters.length() > 0) {
                    String text = "Invalid character entered (" + invalidCharacters.toString() + ")";
                    Toast toast = Toast.makeText( getApplicationContext(), text, Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if (answer.toString().equals(selectedWord)) {
                    // TODO: Mark as done, Toast You Win!
                }
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
