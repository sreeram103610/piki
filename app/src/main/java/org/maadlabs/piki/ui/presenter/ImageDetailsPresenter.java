package org.maadlabs.piki.ui.presenter;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;

import org.maadlabs.piki.domain.interacter.DownloadImageUseCase;
import org.maadlabs.piki.security.security.AndroidPermission;
import org.maadlabs.piki.ui.view.intf.ImageInfoViewModel;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by brainfreak on 10/31/17.
 */

public class ImageDetailsPresenter implements Presenter<ImageInfoViewModel>, ActivityCompat.OnRequestPermissionsResultCallback {

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
                mImageInfoViewModel.addStoragePermission(this);
                boolean storagePermission = mImageInfoViewModel.showRequestPermission();
                if (storagePermission)
                    downloadImage();

                break;
            case SHARE:
        }
    }

    private void downloadImage() {
        File cacheFile = mImageInfoViewModel.getImage();
        mDownloadImageUseCase.setImageToDownload(cacheFile);
        mDownloadImageUseCase.execute(new DownloadImageObserver());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case AndroidPermission.WRITE_STORAGE_REQUEST:
                downloadImage();
                break;
        }
    }

    private final class DownloadImageObserver extends DisposableObserver<File> {

        File mFile;

        @Override
        public void onNext(File file) {
            mFile = file;
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {
            if (mFile != null)
                showNotification(mFile);
        }
    }

    private void showNotification(File file) {
        mImageInfoViewModel.showDownloadNotification(file);
    }
}
