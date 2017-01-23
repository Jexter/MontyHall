package se.android.krumelur.montyhallexperiment.model;

import java.util.ArrayList;

public class Game {
    private final static int NUMBER_OF_CHESTS_IN_GAME = 3; // Keep this at 3 tbh
    private ArrayList<Chest> chests = new ArrayList<>();
    private Chest mChosenChest = null;

    public Game() {
        setupChests();
    }

    private void setupChests() {
        for (int i = 0; i < NUMBER_OF_CHESTS_IN_GAME; i++) {
            chests.add(new Chest());
        }

        int numberOfChestToContainGold = (int) (Math.random() * (double) NUMBER_OF_CHESTS_IN_GAME);

        chests.get(numberOfChestToContainGold).setContainsGold();
    }

    public void chooseAChestAtRandom() {
        int chosenChestNumber = (int) (Math.random() * (double) NUMBER_OF_CHESTS_IN_GAME);
        mChosenChest = chests.get(chosenChestNumber);
    }

    public void doNotChangeChosenChest() {
        // :)
    }

    public void openAnEmptyChest() {
        for (Chest chest : chests) {
            if (!chest.containsGold() && chest != mChosenChest) {
                chest.open();
                return;
            }
        }
    }

    public boolean doesChosenChestContainGold() {
        for (Chest chest : chests) {
            if (chest == mChosenChest) {
                return chest.containsGold();
            }
        }

        // If this happens, either the player hasn't chosen any chest or all the chests have been
        // opened already :-O
        return false;
    }
}
