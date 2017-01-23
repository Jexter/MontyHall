package se.android.krumelur.montyhallexperiment.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import se.android.krumelur.montyhallexperiment.model.Game;

public class GameTask extends AsyncTask<Void, Void, Void> {

    ProgressDialog progressDialog;
    Context mContext;
    private GameCallback mGameCallback;
    private int mNumberOfGames;
    private ArrayList<Game> mGames = new ArrayList<>();
    private int mGamesWonWithoutChangingChest = 0;
    private int mGamesWonByChangingChest = 0;

    public GameTask(Context context, GameCallback gameCallback, int numberOfGames) {
        mContext = context;
        mGameCallback = gameCallback;
        mNumberOfGames = numberOfGames;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            //Thread.sleep(1000);

            setupGames();
            playAllGamesStickingToSameChest();

            setupGames();
            playAllGamesAndChangeChests();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private void playAllGamesAndChangeChests() {
        for (Game game : mGames) {
            game.chooseAChestAtRandom();
            game.openAnEmptyChest();
            game.changeChosenChest();

            boolean foundGold = game.doesChosenChestContainGold();

            if (foundGold) {
                mGamesWonByChangingChest++;
            }
        }
    }

    private void playAllGamesStickingToSameChest() {
        for (Game game : mGames) {
            game.chooseAChestAtRandom();
            game.openAnEmptyChest();
            game.doNotChangeChosenChest();

            boolean foundGold = game.doesChosenChestContainGold();

            if (foundGold) {
                mGamesWonWithoutChangingChest++;
            }
        }
    }

    private void setupGames() {
        mGames.clear();

        for (int i = 0; i < mNumberOfGames; i++) {
            mGames.add(new Game());
        }
    }

    @Override
    protected void onPostExecute(Void result) {
        // execution of result of Long time consuming operation
        progressDialog.dismiss();
        mGameCallback.gameComplete(mNumberOfGames, mGamesWonWithoutChangingChest, mGamesWonByChangingChest);
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(mContext,
                "Running Monty Hall Experiment!",
                "Calculating");
    }

    public interface GameCallback {
        void gameComplete(int numberOfGames, int goldFoundWithoutChangingChest, int goldFoundByChangingChest);
    }
}