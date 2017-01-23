package se.android.krumelur.montyhallexperiment.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class GameTask extends AsyncTask<String, String, String> {

    ProgressDialog progressDialog;
    Context mContext;
    private GameCallback mGameCallback;

    public GameTask(Context context, GameCallback gameCallback) {
        mContext = context;
        mGameCallback = gameCallback;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Done";
    }


    @Override
    protected void onPostExecute(String result) {
        // execution of result of Long time consuming operation
        progressDialog.dismiss();
        mGameCallback.gameComplete();
    }


    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(mContext,
                "Running Monty Hall Experiment!",
                "Calculating");
    }


    @Override
    protected void onProgressUpdate(String... text) {
    }

    public interface GameCallback {
        void gameComplete();
    }
}