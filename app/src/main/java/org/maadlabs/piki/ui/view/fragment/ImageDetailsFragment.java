package org.maadlabs.piki.ui.view.fragment;


import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.maadlabs.piki.R;
import org.maadlabs.piki.ui.model.ImageDataModel;
import org.maadlabs.piki.ui.presenter.ImageDetailsPresenter;
import org.maadlabs.piki.ui.view.intf.LoadingInterface;
import org.maadlabs.piki.ui.view.intf.ViewImageInfoModel;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageDetailsFragment extends Fragment implements ViewImageInfoModel<ImageDataModel>{

    Toolbar mToolbar;

    ImageDataModel mImageData;

    @Inject
    ImageDetailsPresenter mImageDetailsPresenter;

    @Inject
    @Named("MainActivityContext")
    Context mContext;
    private View mView;
    private ImageView mImageView;

    public ImageDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_image_details, container, false);
        mImageView = (ImageView) mView.findViewById(R.id.image_view);
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
            }
        }
    }

    @Override
    public void showImage() {
        if (mImageData != null && mImageData.getUri() != null)
            Glide.with(getContext()).load(mImageData.getUri()).into(mImageView);
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
    public void setImage(ImageDataModel imageModel) {
        mImageData = imageModel;
    }
}
