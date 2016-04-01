package edu.westga.rnrscramble;

import android.test.ActivityInstrumentationTestCase2;

import edu.westga.rnrscramble.view.MainActivity;

/**
 * Created by RyanT on 3/31/2016.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTest() {
        super(MainActivity.class);
    }

    public void testActivityExists() {
        MainActivity activity = getActivity();
        assertNotNull(activity);
    }
}
