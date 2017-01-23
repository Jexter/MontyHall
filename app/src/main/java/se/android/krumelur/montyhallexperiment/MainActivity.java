package se.android.krumelur.montyhallexperiment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import se.android.krumelur.montyhallexperiment.tasks.GameTask;

public class MainActivity extends AppCompatActivity {

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

        final GameTask.GameCallback gameCallback = new GameTask.GameCallback() {
            @Override
            public void gameComplete(int hej) {
                updateResultsTextView(hej);            }
        };

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

    private void updateResultsTextView(int numberOfGamesWon) {
        TextView resultsTextView = (TextView) findViewById(R.id.experiments_results);

        if (!TextUtils.isEmpty(resultsTextView.getText())) {
            resultsTextView.setText(resultsTextView.getText() + "\n");
        }

        resultsTextView.setText(resultsTextView.getText() + String.valueOf(numberOfGamesWon) + " gold found");
    }
}
