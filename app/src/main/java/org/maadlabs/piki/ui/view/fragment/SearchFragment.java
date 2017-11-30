package org.maadlabs.piki.ui.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import org.maadlabs.piki.R;
import org.maadlabs.piki.data.di.ApiModule;
import org.maadlabs.piki.data.di.ImageDataRepositoryModule;
import org.maadlabs.piki.ui.di.MyModule;
import org.maadlabs.piki.ui.model.ImageDataModel;
import org.maadlabs.piki.ui.navigator.Navigator;
import org.maadlabs.piki.ui.presenter.SearchImagesPresenter;
import org.maadlabs.piki.ui.view.intf.LoadingInterface;
import org.maadlabs.piki.ui.view.intf.SearchableViewModel;
import org.maadlabs.piki.ui.view.adapter.ImagesListAdapter;
import org.maadlabs.piki.ui.view.intf.ToolbarCallback;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SearchFragment extends Fragment implements SearchableViewModel, ImagesListAdapter.ItemInfoListener {


    private static final String SEARCH_VIEW_STRING = "SearchView";
    @BindView(R.id.images_recyclerview)
    RecyclerView mImagesRecyclerView;

    @Inject
    ImagesListAdapter mImagesListAdapter;

    @Inject
    Context mContext;

    @Inject
    SearchImagesPresenter mPresenter;

    Navigator mNavigator;

    LoadingInterface mLoadingInterface;
    private String mSearchQuery;
    private boolean mViewCreated;
    private Bundle mStateBundle;
    private ToolbarCallback mToolbarData;

    @Inject
    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState != null)
            mStateBundle = savedInstanceState;

        Log.i("SFragContext", mContext.hashCode() + "");

        View view = inflater.inflate(R.layout.fragment_image_grid, container, false);
        ButterKnife.bind(this, view);
        mLoadingInterface = (LoadingInterface) getActivity();
        mNavigator = Navigator.getInstance();

        if (getActivity() instanceof ToolbarCallback)
            mToolbarData = (ToolbarCallback) getActivity();
        mToolbarData.onRestoreToolbarData(savedInstanceState);
        return view;
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

    }

    @Override
    public void setQuery(String query) {

        mSearchQuery = query;
        if (mViewCreated)
            mPresenter.onSearchQuery(mSearchQuery);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        if (mToolbarData != null) {
             outState = mToolbarData.onSaveToolbarData();
        }
        super.onSaveInstanceState(outState);
    }

    private void initViews() {
        mImagesListAdapter.setItemInfoListener(this);
        Log.i("Context Addr  Frag = ", mContext.hashCode() + "");
        mImagesRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        mImagesRecyclerView.setAdapter(mImagesListAdapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();
        mPresenter.setView(this);
        mPresenter.onSearchQuery(mSearchQuery);
        mViewCreated = true;
    }

    @Override
    public void onImageClicked(ImageDataModel model) {

        mNavigator.navigateToViewImage(getActivity(), model);
    }

    @Override
    public void loadImages(List<ImageDataModel> imageList) {
        mImagesRecyclerView.setVisibility(View.VISIBLE);
        mImagesListAdapter.setCollection(imageList, true);
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
    public void onStop() {
        super.onStop();

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
