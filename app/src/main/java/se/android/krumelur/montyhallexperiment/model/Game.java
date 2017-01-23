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

        chests.get(numberOfChestToContainGold).putSomeGoldIn();
    }

    public void chooseAChestAtRandom() {
        int chosenChestNumber = (int) (Math.random() * (double) NUMBER_OF_CHESTS_IN_GAME);
        mChosenChest = chests.get(chosenChestNumber);
    }

    public void doNotChangeChosenChest() {
        // :)
    }

    public void openAnEmptyUnchosenChest() {
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

        // If this happens the player hasn't chosen any chest or there's no gold in any of them
        return false;
    }

    public void changeChosenChest() {
        for (Chest chest : chests) {
            if (chest != mChosenChest) {
                if (!chest.opened()) {
                    mChosenChest = chest;
                    return;
                }
            }
        }
    }
}
