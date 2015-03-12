package au.amir.personal.facts.activity;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import au.amir.personal.facts.R;


public abstract class AbstractFragmentActivity extends ActionBarActivity {

    private static final String TAG = "AbsFragmentActivity";
    public static final String CURRENT_FRAGMENT_TAG = "currentFragment";
    public static final int LOADING_DIALOG = 123;

    protected boolean pendingBackToRootFragment = false;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "LOG onSaveInstanceState called");
        outState.putBoolean("pendingBackToRootFragment", pendingBackToRootFragment);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "LOG onRestoreInstanceState called");
        pendingBackToRootFragment = savedInstanceState.getBoolean("pendingBackToRootFragment");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* requestWindowFeature(Window.FEATURE_NO_TITLE);

        navigateTo(getNewMainFragment(), false);*/


    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"LOG on start absfragactivity");
    }

        @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,"LOG on stop absfragactivity");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"LOG on resume absfragactivity");
        Log.d(TAG, "Should pop back? " + pendingBackToRootFragment);
        if (pendingBackToRootFragment) {
            // popToRoot();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"LOG on pause absfragactivity");
    }

    public void popToRoot() {
        Log.d(TAG,"LOG on poptoROOT absfragactivity");
        final FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() == 0) return;
        int rootFragment = manager.getBackStackEntryAt(0).getId();
        manager.popBackStackImmediate(rootFragment, 0);
        pendingBackToRootFragment = false;
    }

    private void navigateTo(Fragment newFragment, boolean animate) {
        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction ft = manager.beginTransaction();

        ft.replace(R.id.container, newFragment, CURRENT_FRAGMENT_TAG);
        // Add this trnasaction to the back stack, so when the user press back,
        // it rollbacks.
        ft.addToBackStack(null);
        ft.commit();
    }

    private void navigateTo(Fragment newFragment) {
        navigateTo(newFragment, true);
    }

    protected void navigateTo(Fragment newFragment,int Position) {
        Bundle args =null;
        if (newFragment.getArguments()==null)
              args = new Bundle();
        else
             args = newFragment.getArguments();

        newFragment.setArguments(args);
        navigateTo(newFragment, true);
    }


    @Override
    public void onBackPressed() {
        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 1) {
            // If there are back-stack entries, leave the FragmentActivity
            // implementation take care of them.
            super.onBackPressed();
            Log.d(TAG,"LOG on onBackPressed 1 absfragactivity");
        } else {
            Log.d(TAG,"LOG on onBackPressed 2 absfragactivity");

          finish();
        }
    }


    public boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager)  getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) { return true; }
        return false;
    }
}
