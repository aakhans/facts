package au.amir.personal.facts.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import au.amir.personal.facts.R;
import au.amir.personal.facts.activity.AbstractFragment;


/**
 * Created by amir on 2/6/2015.
 */
public class HomeFragment extends AbstractFragment  {

    EditText  SearchQuery =null;
    private static final String TAG = HomeFragment.class.getName();

    @Override
    public String getHeaderName() {
        return null;
    }

    @Override
    protected int getViewFragmentId() {
        return R.layout.home_fragment;
    }

    @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);



        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "LOG on start homefrag");
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "LOG on pause homefrag");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "LOG on stop homefrat");
    }


}
