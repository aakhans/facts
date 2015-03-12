package au.amir.personal.facts.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import au.amir.personal.facts.R;
import au.amir.personal.facts.fragment.HomeFragment;
import au.amir.personal.facts.service.APIService;
import au.amir.personal.facts.service.enums.IntentEnums;
import au.amir.personal.facts.service.enums.WebCommandEnums;


public class MainActivity extends AbstractFragmentActivity {

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#009688")));

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new HomeFragment())
                    .commit();
        }

        Intent intent = new Intent(this, APIService.class);
        intent.putExtra(IntentEnums.WSURL.name(), getResources().getString(R.string.EndPointURL));
        intent.putExtra(IntentEnums.COMMAND.name(), WebCommandEnums.GET_DATA);
       // intent.putExtra(IntentEnums.RECEIVER.name(), mReceiver);
        startService(intent);
        Log.d(TAG, " Service Launched... ");

    }

   /* @Override
    public void onNavigationDrawerItemSelected(int position) {


        switch(position){
            case 0:
                    navigateTo(new HomeFragment(), position);

                    break;
             case 1:
                if (!isConnected())
                    {
                    Toast toast = Toast.makeText( getApplicationContext(),"Please check your internet settings and try again", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                    }
                 navigateTo(new CatalogueFragment(),position);
                    break;
            case 2:
                if (!isConnected())
                {
                    Toast toast = Toast.makeText( getApplicationContext(),"Please check your internet settings and try again", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                }

                navigateTo(new MapsFragment(), position);
                break;
        }

    }*/


    public void restoreActionBar() {

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(getResources().getString(R.string.app_name));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
            restoreActionBar();
            return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
