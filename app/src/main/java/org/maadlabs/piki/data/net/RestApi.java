package org.maadlabs.piki.data.net;


import org.maadlabs.piki.domain.entity.ImageData;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by brainfreak on 10/10/17.
 */

public interface RestApi {

    Observable<List<ImageData>> searchImagesList(String query, int limit);

    Observable<List<ImageData>> trendingImagesList(int limit);
}
