package edu.westga.rnrscramble.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
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
        setScrambleText(scrambledWord);
        setAnswerText("");
    }

    public void NewWordClicked(View Sender) {
        selectedWord = wordGenerator.nextWord(selectedLength);
        scrambledWord = WordScrambler.Scramble(selectedWord);
        setScrambleText(scrambledWord);
        setAnswerText("");
    }

    private void setScrambleText(String word) {
        TextView textView = (TextView) findViewById(R.id.scramble_text);
        textView.setText(word);
    }

    private void setAnswerText(String word) {
        TextView textView = (TextView) findViewById(R.id.answer_text);
        textView.setText(word);
    }
}
