package org.maadlabs.piki.domain.interacter;

import org.maadlabs.piki.data.repository.ImageDataRepository;
import org.maadlabs.piki.domain.entity.ImageData;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by brainfreak on 10/12/17.
 */

public class SearchImageListUseCase extends UseCase<List<ImageData>> {

    private static final int SEARCH_LIMIT = 10;

    private String mSearchKey;

    @Inject
    ImageDataRepository mImageDataRepository;

    @Inject
    public SearchImageListUseCase() {

    }

    @Override
    protected Observable<List<ImageData>> buildObservable() {

        return mImageDataRepository.search(mSearchKey, SEARCH_LIMIT);
    }

    public void setQuery(String query) {
        mSearchKey = query;
    }
}
