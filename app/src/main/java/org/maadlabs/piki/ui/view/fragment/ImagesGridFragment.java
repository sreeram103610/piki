package org.maadlabs.piki.ui.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.maadlabs.piki.R;
import org.maadlabs.piki.ui.di.DaggerImagesGridComponent;
import org.maadlabs.piki.ui.di.ImagesGridComponent;
import org.maadlabs.piki.ui.di.ImagesGridModule;
import org.maadlabs.piki.ui.model.ImageDataModel;
import org.maadlabs.piki.ui.presenter.ImageDetailsPresenter;
import org.maadlabs.piki.ui.view.ImageDataViewModel;
import org.maadlabs.piki.ui.view.LoadingInterface;
import org.maadlabs.piki.ui.view.adapter.ImagesListAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImagesGridFragment extends Fragment implements ImageDataViewModel, SearchView.OnQueryTextListener {

    @BindView(R.id.images_recyclerview) RecyclerView mImagesRecyclerView;
    @BindView(R.id.image_search_view) SearchView mSearchView;

    @Inject
    ImagesListAdapter mImagesListAdapter;
    @Inject
    Context mContext;
    @Inject
    ImageDetailsPresenter mPresenter;

    LoadingInterface mLoadingInterface;

    public ImagesGridFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_image_grid, container, false);
        ButterKnife.setDebug(true);
        ButterKnife.bind(this, view);
        mSearchView.setOnQueryTextListener(this);
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
        DaggerImagesGridComponent.builder().imagesGridModule(new ImagesGridModule(getContext())).build().inject(this);
        initViews();
        mPresenter.setView(this);
        mPresenter.init();
    }

    @Override
    public void onImageClicked(int imageId) {

    }

    @Override
    public void loadImages(List<ImageDataModel> imageList) {
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
    public boolean onQueryTextSubmit(String query) {

        mPresenter.onSearchQuery(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
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
