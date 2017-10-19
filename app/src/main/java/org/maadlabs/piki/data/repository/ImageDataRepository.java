package org.maadlabs.piki.data.repository;

import org.maadlabs.piki.data.di.DaggerImageDataRepositoryComponent;
import org.maadlabs.piki.domain.entity.ImageData;
import org.maadlabs.piki.data.mapper.ImageDataMapper;
import org.maadlabs.piki.data.source.ImageDataSource;
import org.maadlabs.piki.data.source.ImageDataSourceFactory;
import org.maadlabs.piki.domain.repository.ImageRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by brainfreak on 10/10/17.
 */

public class ImageDataRepository implements ImageRepository {

    private ImageDataSourceFactory mImageDataSourceFactory;
    @Inject
    ImageDataSource mImageDataSource;

    @Inject
    public ImageDataRepository(ImageDataSourceFactory imageDataSourceFactory) {
        mImageDataSourceFactory = imageDataSourceFactory;
        DaggerImageDataRepositoryComponent.builder().build().inject(this);
    }

    @Override
    public Observable<List<ImageData>> search(String query, int limit) {
        return mImageDataSource.search(query, limit);
    }
}
