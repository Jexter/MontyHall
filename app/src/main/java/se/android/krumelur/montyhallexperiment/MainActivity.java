package se.android.krumelur.montyhallexperiment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    protected void onStart() {
        super.onStart();

        TextView resultsTextView = (TextView) findViewById(R.id.experiments_results);
        resultsTextView.setText(resultsTextView.getText() + "\n" + "Hej");


    }
}
