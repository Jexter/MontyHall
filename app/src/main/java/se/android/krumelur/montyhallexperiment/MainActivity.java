package se.android.krumelur.montyhallexperiment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import se.android.krumelur.montyhallexperiment.tasks.GameTask;

public class MainActivity extends AppCompatActivity {

    TextView mResultsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();


        Button runGameButton = (Button) findViewById(R.id.run_button);

        final EditText numberOfGamesEditText = (EditText) findViewById(R.id.experiment_number_edittext);
        numberOfGamesEditText.setSelection(numberOfGamesEditText.getText().length());

        View clearButton = findViewById(R.id.clear_button);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mResultsTextView.setText("");
            }
        });

        final GameTask.GameCallback gameCallback = new GameTask.GameCallback() {
            @SuppressLint("SetTextI18n")
            @Override
            public void gameComplete(int numberOfGames, int goldFoundWithoutChangingChest, int goldFoundByChangingChest) {
                if (!TextUtils.isEmpty(mResultsTextView.getText())) {
                    mResultsTextView.setText(mResultsTextView.getText() + "\n");
                }
                mResultsTextView.setText(mResultsTextView.getText() + "Sticking with first choice: " + String.valueOf(goldFoundWithoutChangingChest) + "\n");
                mResultsTextView.setText(mResultsTextView.getText() + "Switching chest: " + String.valueOf(goldFoundByChangingChest) + "\n");
                mResultsTextView.setText(mResultsTextView.getText() + "Result: " + (goldFoundWithoutChangingChest > goldFoundByChangingChest ? "Don't change chest!" : "Change chest for best chance of winning!"));
                mResultsTextView.setText(mResultsTextView.getText() + "\n\n");


                        // Tragic bit of necessary code
                final ScrollView scrollView = (ScrollView) findViewById(R.id.results_scroll_container);
                scrollView.post(new Runnable() {
                    public void run() {
                        scrollView.fullScroll(View.FOCUS_DOWN);
                    }
                });
            }
        };

        mResultsTextView = (TextView) findViewById(R.id.experiments_results);

        runGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numberOfGamesString = numberOfGamesEditText.getText().toString();

                try {
                    int numberOfGames = Integer.valueOf(numberOfGamesString);

                    if (numberOfGames <= 0) {
                        return;
                    } else {
                        new GameTask(MainActivity.this, gameCallback, numberOfGames).execute();
                    }
                } catch(NumberFormatException nfe) {
                    Log.d("Can't run game", nfe.getMessage());
                }
            }
        });
    }
}
