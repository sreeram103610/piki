package org.maadlabs.piki.data.net.imgur;

import org.maadlabs.piki.data.net.RestApi;
import org.maadlabs.piki.domain.entity.ImageData;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Created by brainfreak on 10/16/17.
 */

public class ImgurAPIClient implements RestApi{

    @Inject
    Retrofit mRetrofit;
    @Inject
    ImgurAPI mImgurAPI;

    public ImgurAPIClient() {
    }

    // TODO: Remove retrofit
    @Override
    public Observable<List<ImageData>> imageList(String query, int limit) {
        return Observable.fromCallable(new Callable<List<ImageData>>() {
            @Override
            public List<ImageData> call() throws Exception {
                return null;
            }
        });
    }
}
