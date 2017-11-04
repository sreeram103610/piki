package org.maadlabs.piki.ui.view.fragment;


import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.maadlabs.piki.R;
import org.maadlabs.piki.ui.view.intf.ViewImageInfoModel;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageDetailsFragment extends Fragment implements ViewImageInfoModel{

    Toolbar mToolbar;

    @Inject
    @Named("MainActivityContext")
    Context mContext;

    public ImageDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        initToolbar();
        return inflater.inflate(R.layout.fragment_image_details, container, false);
    }

    private void initToolbar() {

        FragmentActivity activity = getActivity();
        if (activity != null) {
            ActionBar actionBar = activity.getActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void hideError() {

    }

    @Override
    public void onClick(ButtonType buttonType) {

    }

    @Override
    public void setImage(Object imageModel) {

    }
}
