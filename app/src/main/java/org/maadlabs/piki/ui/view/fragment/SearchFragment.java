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
import org.maadlabs.piki.data.di.ApiModule;
import org.maadlabs.piki.data.di.ImageDataRepositoryModule;
import org.maadlabs.piki.ui.di.DaggerFragmentComponent;
import org.maadlabs.piki.ui.di.MyModule;
import org.maadlabs.piki.ui.model.ImageDataModel;
import org.maadlabs.piki.ui.navigator.Navigator;
import org.maadlabs.piki.ui.presenter.SearchImagesPresenter;
import org.maadlabs.piki.ui.view.intf.LoadingInterface;
import org.maadlabs.piki.ui.view.intf.SearchableViewModel;
import org.maadlabs.piki.ui.view.adapter.ImagesListAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SearchFragment extends Fragment implements SearchableViewModel, ImagesListAdapter.ItemInfoListener {


    @BindView(R.id.images_recyclerview)
    RecyclerView mImagesRecyclerView;

    @Inject
    ImagesListAdapter mImagesListAdapter;

    @Inject
    Context mContext;

    @Inject
    SearchImagesPresenter mPresenter;

    @Inject
    Navigator mNavigator;

    LoadingInterface mLoadingInterface;
    private String mSearchQuery;

    public SearchFragment() {
        // Required empty public constructor
    }


    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_grid, container, false);
        ButterKnife.bind(this, view);
        mLoadingInterface = (LoadingInterface) getActivity();
        return view;
    }

    @Override
    public void setQuery(String query) {

        mSearchQuery = query;
        if (mPresenter != null) // Fragment already on top
            mPresenter.onSearchQuery(query);
    }


    private void initViews() {
        mImagesListAdapter.setItemInfoListener(this);
        mImagesRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        mImagesRecyclerView.setAdapter(mImagesListAdapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DaggerFragmentComponent.builder().apiModule(new ApiModule()).imageDataRepositoryModule(new ImageDataRepositoryModule())
                .myModule(new MyModule(getContext())).build().inject(this);
        initViews();
        mPresenter.setView(this);
        if (mSearchQuery != null)
            mPresenter.onSearchQuery(mSearchQuery);
    }

    @Override
    public void onImageClicked(ImageDataModel model) {

        mNavigator.navigateToViewImage(getActivity(), model);
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


    @Override
    public void onItemClick(int position, ImageDataModel model) {
        onImageClicked(model);
    }

    @Override
    public void onMaxItemsLimitReached(int position) {

    }
}
