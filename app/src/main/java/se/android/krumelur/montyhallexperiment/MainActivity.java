package se.android.krumelur.montyhallexperiment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
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

        final GameTask.GameCallback gameCallback = new GameTask.GameCallback() {
            @Override
            public void gameComplete() {
                TextView resultsTextView = (TextView) findViewById(R.id.experiments_results);

                if (!TextUtils.isEmpty(resultsTextView.getText())) {
                    resultsTextView.setText(resultsTextView.getText() + "\n");
                }

                resultsTextView.setText(resultsTextView.getText() + "6 successful games");
            }
        };

        runGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GameTask(MainActivity.this, gameCallback).execute();
            }
        });
    }
}
