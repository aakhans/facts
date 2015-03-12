package au.amir.personal.facts.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import au.amir.personal.facts.R;


public class SplashScreenActivity extends AbstractFragmentActivity {

    private static final int SPLASH_DISPLAY_TIME = 3000;  /* 3 seconds */
    private static final String TAG = SplashScreenActivity.class.getName();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash_screen);

            new Handler().postDelayed(new Runnable() {

                public void run() {
					
					/* Create an intent that will start the main activity. */
                    Intent mainIntent = new Intent(SplashScreenActivity.this, MainActivity.class);

                    startActivity(mainIntent);
					/* Finish splash activity so user cant go back to it. */
                    finish();
					

                }
            }, SPLASH_DISPLAY_TIME);

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "LOG on start splash");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "LOG on pause splash");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "LOG on stop splash");
    }


}
