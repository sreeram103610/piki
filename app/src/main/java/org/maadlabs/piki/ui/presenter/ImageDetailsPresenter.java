package org.maadlabs.piki.ui.presenter;

import org.maadlabs.piki.domain.interacter.DownloadImageUseCase;
import org.maadlabs.piki.ui.model.ImageDataModel;
import org.maadlabs.piki.ui.view.intf.ImageInfoViewModel;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by brainfreak on 10/31/17.
 */

public class ImageDetailsPresenter implements Presenter<ImageInfoViewModel> {

    ImageInfoViewModel mImageInfoViewModel;

    @Inject
    DownloadImageUseCase mDownloadImageUseCase;

    @Inject
    public ImageDetailsPresenter() {
    }

    @Override
    public void setView(ImageInfoViewModel view) {
        mImageInfoViewModel = view;
        loadView();
    }

    private void loadView() {
        mImageInfoViewModel.showImage();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void destory() {

    }

    public void onButtonClicked(ImageInfoViewModel.ButtonType buttonType) {
        switch (buttonType) {
            case DOWNLOAD:
                // must ask storage permissions before download
                File cacheFile = mImageInfoViewModel.getImage();
                mDownloadImageUseCase.setImageToDownload(cacheFile);
                mDownloadImageUseCase.execute(new DownloadImageObserver());
                break;
            case SHARE:
        }
    }

    private final class DownloadImageObserver extends DisposableObserver<File> {

        @Override
        public void onNext(File file) {
            if (file != null)
                showNotification();
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }

    private void showNotification() {

    }
}
