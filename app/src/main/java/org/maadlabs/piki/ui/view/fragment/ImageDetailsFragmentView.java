package org.maadlabs.piki.ui.view.fragment;


import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.maadlabs.piki.R;
import org.maadlabs.piki.ui.di.MyModule;
import org.maadlabs.piki.ui.model.ImageDataModel;
import org.maadlabs.piki.ui.presenter.ImageDetailsPresenter;
import org.maadlabs.piki.ui.view.intf.ImageInfoViewModel;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageDetailsFragmentView extends Fragment implements ImageInfoViewModel {

    Toolbar mToolbar;

    ImageDataModel mImageData;

    @Inject
    ImageDetailsPresenter mImageDetailsPresenter;

    @Inject
    Context mContext;

    private View mView;

    @BindView(R.id.image_view)
    ImageView mImageView;

    @Inject
    public ImageDetailsFragmentView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i("IDFragContext", mContext.hashCode() + "");

        mView = inflater.inflate(R.layout.fragment_image_details, container, false);
        ButterKnife.bind(this, mView);
        initToolbar();
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mImageDetailsPresenter.setView(this);
    }

    private void initToolbar() {

        FragmentActivity activity = getActivity();
        if (activity != null) {
            ActionBar actionBar = activity.getActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setHomeButtonEnabled(true);
            }
        }
    }

    @Override
    public void showImage() {
        if (mImageData != null && mImageData.getUri() != null)
            Glide.with(mContext).load(mImageData.getUri()).into(mImageView);
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

        switch(buttonType) {
            case SHARE:
                break;
            case DOWNLOAD:

                break;
        }
    }

    @Override
    public void setImage(ImageDataModel imageModel) {
        mImageData = imageModel;
    }
}
