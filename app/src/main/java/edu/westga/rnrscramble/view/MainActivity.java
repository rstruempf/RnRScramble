package edu.westga.rnrscramble.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import edu.westga.rnrscramble.R;
import edu.westga.rnrscramble.model.HardCodedWordList;
import edu.westga.rnrscramble.model.IWordGenerator;
import edu.westga.rnrscramble.model.WordScrambler;

public class MainActivity extends AppCompatActivity {

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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        scrambleTextView = (TextView) findViewById(R.id.scramble_text);
        answerTextView = (EditText) findViewById(R.id.answer_text);
        answerTextView.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO
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
        selectedWord = wordGenerator.nextWord(selectedLength);
        scrambledWord = WordScrambler.Scramble(selectedWord);
        setScrambleTextView(scrambledWord);
        setAnswerTextView("");
    }

    private void setScrambleTextView(String word) {
        scrambleTextView.setText(word);
    }

    private void setAnswerTextView(String word) {
        answerTextView.setText(word);
    }
}
