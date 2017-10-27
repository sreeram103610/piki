package org.maadlabs.piki.ui.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.maadlabs.piki.R;
import org.maadlabs.piki.data.di.ImageDataRepositoryModule;
import org.maadlabs.piki.ui.di.DaggerImagesGridComponent;
import org.maadlabs.piki.ui.di.ImagesGridModule;
import org.maadlabs.piki.ui.model.ImageDataModel;
import org.maadlabs.piki.ui.presenter.ImageDetailsPresenter;
import org.maadlabs.piki.ui.view.TrendingDataViewModel;
import org.maadlabs.piki.ui.view.LoadingInterface;
import org.maadlabs.piki.ui.view.adapter.ImagesListAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrendingImagesFragment extends Fragment implements TrendingDataViewModel {

    public static final String TAG = "ImagesGridFragment";
    @BindView(R.id.images_recyclerview) RecyclerView mImagesRecyclerView;

    @Inject
    ImagesListAdapter mImagesListAdapter;
    @Inject
    Context mContext;
    @Inject
    ImageDetailsPresenter mPresenter;

    LoadingInterface mLoadingInterface;

    public TrendingImagesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_image_grid, container, false);
        ButterKnife.setDebug(true);
        ButterKnife.bind(this, view);
        mLoadingInterface = (LoadingInterface) getActivity();
        return view;
    }

    private void initViews() {
        mImagesRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        mImagesRecyclerView.setAdapter(mImagesListAdapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        mPresenter.setView(this);
        mPresenter.init();
    }

    @Override
    public void onImageClicked(int imageId) {

    }

    @Override
    public void loadImages(List<ImageDataModel> imageList) {
        mImagesRecyclerView.setVisibility(View.VISIBLE);
        mImagesListAdapter.setCollection(imageList);
    }

    @Override
    public void showLoading() {
        mLoadingInterface.showLoading();
    }

    @Override
    public void hideLoading() {
        mLoadingInterface.hideLoading();
    }

    @Override
    public void showRetry() {
        mLoadingInterface.showRetry();
    }

    @Override
    public void hideRetry() {
        mLoadingInterface.hideRetry();
    }

    @Override
    public void showError(String errorMessage) {
        mLoadingInterface.showError(errorMessage);
    }

    @Override
    public void init() {

    }

    @Override
    public void hideErrorMessage() {
        mLoadingInterface.hideError();
    }

    @Override
    public void hideImages() {
        mImagesRecyclerView.setVisibility(View.GONE);
    }


    @Override
    public void onPause() {
        super.onPause();
        mPresenter.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.resume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.destory();
    }



}
