package se.android.krumelur.montyhallexperiment.model;

/**
 * Created by atkin on 23/01/2017.
 */

public class Chest {
    private boolean mContainsGold = false;
    private boolean mOpened = false;

    public void setContainsGold() {
        mContainsGold = true;
    }

    public boolean containsGold() {
        return mContainsGold;
    }

    public void open() {
        mOpened = true;
    }

    public boolean opened() {
        return mOpened;
    }
}
