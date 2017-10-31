package org.maadlabs.piki.ui.presenter;


import org.maadlabs.piki.domain.entity.ImageData;
import org.maadlabs.piki.domain.interacter.TrendingImagesUseCase;
import org.maadlabs.piki.ui.mapper.ImageDataModelMapper;
import org.maadlabs.piki.ui.view.TrendingDataViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by brainfreak on 10/14/17.
 */

public class ImageDetailsPresenter implements Presenter<TrendingDataViewModel> {

    ImageDataModelMapper mImageDataMapper;
    TrendingImagesUseCase mTrendingImagesUseCase;

    TrendingDataViewModel mImageDataModel;
    private ImageListObserver mImageListObserver;

    @Inject
    public ImageDetailsPresenter(TrendingImagesUseCase trendingImagesUseCase, ImageDataModelMapper mapper) {
        mImageDataMapper = mapper;
        mTrendingImagesUseCase = trendingImagesUseCase;
    }

    @Override
    public void setView(TrendingDataViewModel model) {
        mImageDataModel = model;
    }

    private void showViewLoading() {
        mImageDataModel.showLoading();
    }

    private void hideViewLoading() {
        mImageDataModel.hideLoading();
    }

    private void showViewRetry() {
        mImageDataModel.showRetry();
    }

    private void hideViewRetry() {
        mImageDataModel.hideRetry();
    }

    private void showErrorMessage(String error) {
        mImageDataModel.showError(error);
    }

    private void hideErrorMessage() { mImageDataModel.hideErrorMessage(); }

    public void init() {

        showViewLoading();
        hideViewRetry();
        mImageListObserver = new ImageListObserver();
        mTrendingImagesUseCase.execute(mImageListObserver);
    }

    private void getImageList(String query) {
        mImageListObserver = new ImageListObserver();
    }

    private void showImageList(List<ImageData> imageList) {
        mImageDataModel.loadImages(mImageDataMapper.map(imageList));
    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void destory() {
        mTrendingImagesUseCase.removeObserver();
    }

    private void hideImages() {
        mImageDataModel.hideImages();
    }

    private final class ImageListObserver extends DisposableObserver<List<ImageData>> {

        private List<ImageData> mImageDataList = new ArrayList<>();

        @Override
        public void onNext(@NonNull List<ImageData> imageDatas) {
            mImageDataList.addAll(imageDatas);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            hideViewLoading();
            hideImages();
            showErrorMessage(e.getMessage());
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
