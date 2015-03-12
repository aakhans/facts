package au.amir.personal.facts.activity;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import au.amir.personal.facts.service.DetachableResultReceiver;


public abstract class AbstractFragment extends Fragment implements DetachableResultReceiver.Receiver {

    private static final String TAG = AbstractFragment.class.getName();

    public abstract String getHeaderName();

    protected abstract int getViewFragmentId();

    public void refreshModel() {
    }

    public void refreshModel(boolean refreshPhone) {
    }

    public void refreshViewFromModel() {
    }

    protected DetachableResultReceiver mReceiver;
    private ProgressDialog progressDialog;

    private boolean destroyed = false;

    protected void navigateTo(AbstractFragment fragment) {
        ((AbstractFragmentActivity) getActivity()).navigateTo(fragment,1);
    }

    protected void navigateTo(AbstractFragment fragment,int position) {
        ((AbstractFragmentActivity) getActivity()).navigateTo(fragment,position);
    }

    public void setupHeaderView() {
       /* View view = getView().findViewById(R.id.headerLbl);
        if (view != null) {
            ((TextView) view).setText(getHeaderName());
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(getViewFragmentId(), container, false);

        mReceiver = new DetachableResultReceiver(new Handler());
        mReceiver.setReceiver(this);

        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupHeaderView();
        refreshViewFromModel();
    }

    public void onPause() {
        super.onPause();
        Log.d(TAG,"LOG on pause absfrag");
        mReceiver.setReceiver(null); // clear receiver so no leaks.
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    public void onResume() {
        super.onResume();
        Log.d(TAG,"LOG on resume absfrag");
        mReceiver.setReceiver(this);
    }

    @Override
    public void onDestroy() {
        destroyed = true;
        if (mReceiver != null) {
            mReceiver.setReceiver(null);
        }
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }

        // cleanup loading dialog in restore instance cases
        try {
            if (getActivity() != null) {
                getActivity().removeDialog(AbstractFragmentActivity.LOADING_DIALOG);
            }
        } catch (IllegalArgumentException e) {
            ; // swallow
        } catch (Exception ex) {
            ; // swallow
        }
        super.onDestroy();
    }


    public  boolean onReceiveResult(int resultCode, Bundle resultData) {

        return false;
    }

    protected boolean handleStatusFinished() {
        switch (1) {
         default:
                return true;
        }
    }


    protected void showAlertDialog(String title, String message) {
        showAlertDialog(title, message, true);
    }

    protected void showAlertDialog(String title, String message, boolean cancelable) {
        showAlertDialog(title, message, cancelable, null);
    }

    protected void showAlertDialog(String title, String message, boolean cancelable, android.content.DialogInterface.OnClickListener clickListener) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(getActivity());
        dlgAlert.setMessage(message);
        dlgAlert.setTitle(title);
        dlgAlert.setPositiveButton("Ok", clickListener);
        dlgAlert.setCancelable(cancelable);
        dlgAlert.create().show();
    }

    public void showProgressDialog() {
        showProgressDialog("");
    }

    public void showProgressDialog(String message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
        }
        progressDialog.setMessage(message);

        // check to make sure we aren't about to finish or have finished
        if (!destroyed) {
            progressDialog.show();
        }
    }

    public void dismissProgressDialog() {
        if (progressDialog != null && !destroyed) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    public boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) { return true; }
        return false;
    }
}
