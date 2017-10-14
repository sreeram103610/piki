package org.maadlabs.piki.data.source;

import org.maadlabs.piki.domain.entity.ImageData;
import org.maadlabs.piki.data.net.RestApi;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by brainfreak on 10/10/17.
 */

public class OnlineDataSource implements ImageDataSource {

    @Inject
    RestApi mRestApi;

    @Override
    public Observable<List<ImageData>> search(String query, int limit) {
        return mRestApi.imageList(query, limit);
    }
}
