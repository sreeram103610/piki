package org.maadlabs.piki.data.source;

import org.maadlabs.piki.domain.entity.ImageData;
import org.maadlabs.piki.data.net.RestApi;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by brainfreak on 10/10/17.
 */

public class OnlineDataSource implements ImageDataSource {

    @Inject
    List<RestApi> mRestApiList;

    public OnlineDataSource() {
    }

    @Override
    public Observable<List<ImageData>> search(final String query, final int limit) {

        List<Observable<List<ImageData>>> observableList = new ArrayList<>();
        for (RestApi api : mRestApiList) {
            observableList.add(api.imageList(query, limit));
        }

        return Observable.mergeDelayError(observableList);
    }
}
