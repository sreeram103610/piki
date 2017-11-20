package org.maadlabs.piki.domain.interacter;

import org.maadlabs.piki.domain.repository.ImageRepository;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by brainfreak on 11/19/17.
 */

public class DownloadImageUseCase extends UseCase<File> {

    File imageToDownload;

    @Inject
    ImageRepository mImageDataRepository;

    @Inject
    public DownloadImageUseCase() {

    }

    public void setImageToDownload(File file) {
        imageToDownload = file;
    }

    @Override
    protected Observable<File> buildObservable() {

        return mImageDataRepository.saveImage(imageToDownload, null);
    }


}
