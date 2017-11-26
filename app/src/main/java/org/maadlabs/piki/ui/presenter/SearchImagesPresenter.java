package org.maadlabs.piki.ui.presenter;

import android.app.Fragment;

import org.maadlabs.piki.R;
import org.maadlabs.piki.domain.entity.ImageData;
import org.maadlabs.piki.domain.interacter.SearchImageListUseCase;
import org.maadlabs.piki.ui.mapper.ImageDataModelMapper;
import org.maadlabs.piki.ui.view.intf.SearchableViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by brainfreak on 10/26/17.
 */

public class SearchImagesPresenter implements Presenter<SearchableViewModel> {

    SearchImageListUseCase mSearchImageListUseCase;
    SearchableViewModel mSearchableViewModel;
    SearchObserver mImageListObserver;
    @Inject
    ImageDataModelMapper mImageDataModelMapper;

    @Inject
    public SearchImagesPresenter(SearchImageListUseCase useCase) {
        mSearchImageListUseCase = useCase;
    }

    @Override
    public void setView(SearchableViewModel model) {
        mSearchableViewModel = model;
        init();
    }

    private void showViewLoading() {
        mSearchableViewModel.showLoading();
    }

    private void hideViewLoading() {
        mSearchableViewModel.hideLoading();
    }

    private void showViewRetry() {
        mSearchableViewModel.showRetry();
    }

    private void hideViewRetry() {
        mSearchableViewModel.hideRetry();
    }

    private void showErrorMessage(String error) {
        mSearchableViewModel.showError(error);
    }

    private void hideErrorMessage() { mSearchableViewModel.hideErrorMessage(); }

    public void init() {

        showViewLoading();
        hideViewRetry();
    }

    private void getImageList(String query) {
        mImageListObserver = new SearchObserver();
        mSearchImageListUseCase.setQuery(query);
        mSearchImageListUseCase.execute(mImageListObserver);
    }

    private void showImageList(List<ImageData> imageList) {
        mSearchableViewModel.loadImages(mImageDataModelMapper.map(imageList));
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void destory() {
        mSearchImageListUseCase.removeObserver();
    }

    private void hideImages() {
        mSearchableViewModel.hideImages();
    }

    public void onSearchQuery(String query) {
        hideViewRetry();
        showViewLoading();
        hideImages();
        getImageList(query);
    }

    private final class SearchObserver extends DisposableObserver<List<ImageData>> {

        private List<ImageData> mImageDataList = new ArrayList<>();

        @Override
        public void onNext(@NonNull List<ImageData> imageDatas) {
            mImageDataList.addAll(imageDatas);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            hideViewLoading();
            hideImages();
            showErrorMessage(((Fragment) mSearchableViewModel).getString(R.string.error_image));
            showViewRetry();
        }

        @Override
        public void onComplete() {
            hideViewLoading();
            hideViewRetry();
            showImageList(mImageDataList);
        }
    }
}
