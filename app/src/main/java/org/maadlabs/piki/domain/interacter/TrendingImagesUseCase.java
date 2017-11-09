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

    private static final int DEFAULT_LIMIT = 10;
    private static final int DEFAULT_OFFSET = 0;

    @Inject
    ImageRepository mImageDataRepository;
    private int mLimit;
    private int mOffset;

    @Inject
    public TrendingImagesUseCase() {
        mLimit = DEFAULT_LIMIT;
        mOffset = DEFAULT_OFFSET;
    }

    public void setLimit(int limit) {
        mLimit = limit;
    }

    public void setOffset(int offset) {
        mOffset = offset;
    }

    @Override
    protected Observable<List<ImageData>> buildObservable() {
        return mImageDataRepository.trending(mLimit, mOffset);
    }

    public int getLimit() {
        return mLimit;
    }
}
