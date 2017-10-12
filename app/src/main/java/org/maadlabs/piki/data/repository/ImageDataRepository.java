package org.maadlabs.piki.data.repository;

import com.giphy.sdk.core.network.response.ListMediaResponse;

import org.maadlabs.piki.data.entity.ImageData;
import org.maadlabs.piki.data.mapper.ImageDataMapper;
import org.maadlabs.piki.data.net.giphy.GPHApiSync;
import org.maadlabs.piki.data.source.ImageDataSource;
import org.maadlabs.piki.data.source.ImageDataSourceFactory;
import org.maadlabs.piki.domain.repository.ImageRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by brainfreak on 10/10/17.
 */

public class ImageDataRepository implements ImageRepository {

    private ImageDataSourceFactory mImageDataSourceFactory;
    private ImageDataMapper mImageDataMapper;

    @Inject
    public ImageDataRepository(ImageDataSourceFactory imageDataSourceFactory, ImageDataMapper imageDataMapper) {
        mImageDataMapper = imageDataMapper;
        mImageDataSourceFactory = imageDataSourceFactory;
    }

    @Override
    public Observable<List<String>> search(String query, int limit) {
        ImageDataSource imageDataSource = mImageDataSourceFactory.createOnlineImageDataSource();
        return imageDataSource.search(query, limit).map(new Function<List<ImageData>, List<String>>() {

            @Override
            public List<String> apply(@NonNull List<ImageData> imageDatas) throws Exception {

                List<String> urlList = new ArrayList<String>();
                for (ImageData imageData : imageDatas)
                    urlList.add(imageData.getUrl());
                return urlList;
            }
        });
    }
}
