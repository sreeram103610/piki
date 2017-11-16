package org.maadlabs.piki.data.source;

import org.maadlabs.piki.domain.entity.ImageData;
import org.maadlabs.piki.data.net.RestApi;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by brainfreak on 10/10/17.
 */

public class OnlineDataSource implements ImageDataSource {

    @Inject
    List<RestApi> mRestApiList;

    @Inject
    public OnlineDataSource() {
    }

    @Override
    public Observable<List<ImageData>> search(final String query, final int limit) {

        List<Observable<List<ImageData>>> observableList = new ArrayList<>();
        for (RestApi api : mRestApiList) {
            Observable<List<ImageData>> images = api.searchImagesList(query, limit);
            if (images != null)
                observableList.add(images);
        }

        return Observable.mergeDelayError(observableList);
    }

    @Override
    public Observable<List<ImageData>> trending(int limit, int offset) {

        List<Observable<List<ImageData>>> observableList = new ArrayList<>();
        for (RestApi api : mRestApiList) {
            Observable<List<ImageData>> images = api.trendingImagesList(limit, offset);
            if (images != null)
                observableList.add(images);
        }

        return Observable.mergeDelayError(observableList);
    }

    @Override
    public Observable<File> saveImage(ImageData imageData) {
        return null;
    }
}
