package au.amir.personal.facts.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import au.amir.personal.facts.R;
import au.amir.personal.facts.activity.AbstractFragment;
import au.amir.personal.facts.service.APIService;
import au.amir.personal.facts.service.MyService;
import au.amir.personal.facts.service.enums.IntentEnums;
import au.amir.personal.facts.service.enums.WebCommandEnums;


/**
 * Created by amir on 2/6/2015.
 */
public class StartupFragment extends AbstractFragment    {

    TextView  txtMsg1;
    TextView  txtMsg2;

    private static final String TAG = HomeFragment.class.getName();

    @Override
    public String getHeaderName() {
        return null;
    }

    @Override
    protected int getViewFragmentId() {
        return R.layout.startup_fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        txtMsg1 = (TextView) view.findViewById(R.id.txtMsg1);
        txtMsg2 = (TextView) view.findViewById(R.id.txtMsg2);
        performRefresh(); // start intent service
        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public boolean onReceiveResult(int resultCode, Bundle resultData) {
        switch (resultCode) {
            case APIService.STATUS_ERROR:
                txtMsg2.setText("Unable To Contact Server, Please Refresh ...");
                txtMsg1.setText("Please Check Internet Connectivity ...");
                break;
            case APIService.STATUS_FINISHED:
                // means data fetched success
                ActionBar actionBar = ((ActionBarActivity)getActivity()).getSupportActionBar();
                actionBar.setTitle(MyService.getInstance().getFactsSheet().getTitle());  // change Actionbar title as required
                navigateTo(new HomeFragment()); // display the RecyclerView now
                break;
        }
        return true;
    }

}
