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
import android.widget.Toast;

import au.amir.personal.facts.R;
import au.amir.personal.facts.activity.AbstractFragment;
import au.amir.personal.facts.service.APIService;
import au.amir.personal.facts.service.MyService;
import au.amir.personal.facts.service.enums.IntentEnums;
import au.amir.personal.facts.service.enums.WebCommandEnums;


/**
 * Created by amir on 2/6/2015.
 */
public class HomeFragment extends AbstractFragment    {

    EditText  SearchQuery =null;
    RecyclerView recyclerView;
    FactsAdapter factsAdapter;
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
        recyclerView = (RecyclerView) view.findViewById(R.id.facts_rc_view);
        factsAdapter = new FactsAdapter(getActivity() );

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        recyclerView.setAdapter(factsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
            case APIService.STATUS_RUNNING:
                //showProgressDialog(WebCommandEnums.GET_CATALOUGE.name()); // progress dialog can be displayed here
                break;
            case APIService.STATUS_FINISHED:
                // means data fetched success
               // dismissProgressDialog();  // close the dialog
                ActionBar actionBar = ((ActionBarActivity)getActivity()).getSupportActionBar();
                actionBar.setTitle(MyService.getInstance().getFactsSheet().getTitle());
                factsAdapter.notifyDataSetChanged();
                break;
        }
        return true;
    }

}
