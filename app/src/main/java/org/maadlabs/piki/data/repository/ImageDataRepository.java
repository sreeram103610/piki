package org.maadlabs.piki.data.repository;

import org.maadlabs.piki.domain.entity.ImageData;
import org.maadlabs.piki.data.mapper.ImageDataMapper;
import org.maadlabs.piki.data.source.ImageDataSource;
import org.maadlabs.piki.data.source.ImageDataSourceFactory;
import org.maadlabs.piki.domain.repository.ImageRepository;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by brainfreak on 10/10/17.
 */

public class ImageDataRepository implements ImageRepository {

    @Inject
    ImageDataSource mImageDataSource;

    @Inject
    public ImageDataRepository() {
    }

    @Override
    public Observable<List<ImageData>> search(String query, int limit) {
        return mImageDataSource.search(query, limit);
    }

    @Override
    public Observable<List<ImageData>> trending(int limit, int offset) {
        return mImageDataSource.trending(limit, offset);
    }

    @Override
    public Observable<File> saveImage(File file, ImageData imageData) {
        return mImageDataSource.saveImage(file, imageData);
    }


}
