package org.maadlabs.piki.ui.presenter;


import org.maadlabs.piki.data.mapper.ImageDataMapper;
import org.maadlabs.piki.domain.entity.ImageData;
import org.maadlabs.piki.domain.interacter.SearchImageListUseCase;
import org.maadlabs.piki.ui.mapper.ImageDataModelMapper;
import org.maadlabs.piki.ui.model.ImageDataModel;
import org.maadlabs.piki.ui.view.ImageDataViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by brainfreak on 10/14/17.
 */

public class ImageDetailsPresenter implements Presenter {

    ImageDataModelMapper mImageDataMapper;
    SearchImageListUseCase mImageListUseCase;
    ImageDataViewModel mImageDataModel;

    @Inject
    public ImageDetailsPresenter(SearchImageListUseCase useCase, ImageDataModelMapper mapper) {
        mImageDataMapper = mapper;
        mImageListUseCase = useCase;
    }

    public void setView(ImageDataViewModel model) {
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

    public void init() {

        hideViewLoading();
        hideViewRetry();
    }

    private void getImageList(String query) {
        ImageListObserver observer = new ImageListObserver();
        mImageListUseCase.setQuery(query);
        mImageListUseCase.execute(observer);
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
        mImageListUseCase.removeObserver();
    }

    public void onSearchQuery(String query) {
        hideViewRetry();
        showViewLoading();
        getImageList(query);
    }

    private final class ImageListObserver extends DisposableObserver<List<ImageData>> {

        @Override
        public void onNext(@NonNull List<ImageData> imageDatas) {
            showImageList(imageDatas);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            hideViewLoading();
            showErrorMessage(e.getMessage());
            showViewRetry();
        }

        @Override
        public void onComplete() {
            hideViewLoading();
        }
    }
}
