package org.maadlabs.piki.domain.interacter;

import org.maadlabs.piki.domain.entity.ImageData;
import org.maadlabs.piki.domain.repository.ImageRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by brainfreak on 10/19/17.
 */

public class TrendingImagesUseCase extends UseCase<List<ImageData>> {

    @Inject
    ImageRepository mImageDataRepository;

    @Inject
    public TrendingImagesUseCase() {

    }

    @Override
    protected Observable<List<ImageData>> buildObservable() {
        return mImageDataRepository.trending();
    }
}
