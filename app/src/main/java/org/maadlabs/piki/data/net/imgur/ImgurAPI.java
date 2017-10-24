package org.maadlabs.piki.data.net.imgur;


import com.google.gson.Gson;

import org.maadlabs.piki.data.net.imgur.model.ImgurSearchRequest;
import org.maadlabs.piki.data.net.imgur.model.ImgurSearchResponse;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by brainfreak on 10/16/17.
 */

public class ImgurAPI {

    private static final String AUTHORIZATION_VALUE = "Client-ID b23fe7cd378c3e0";

    @Inject
    OkHttpClient mOkHttpClient;
    @Inject
    Gson mGson;
    @Named("imgur_base_url")
    String mUrl;

    @Inject
    public ImgurAPI() {
    }

    public Observable<ImgurSearchResponse> search(final ImgurSearchRequest searchRequest) {
        return Observable.fromCallable(new Callable<ImgurSearchResponse>() {
            @Override
            public ImgurSearchResponse call() throws Exception {
                try {
                    Request request = new Request.Builder().url(mUrl + searchRequest.buildUrl(true)).addHeader("Authorization: ", AUTHORIZATION_VALUE).build();

                    Response response = null;
                    response = mOkHttpClient.newCall(request).execute();
                    if (response == null || response.body() == null)
                        return null;    // TODO: Should return a proper error message

                    return mGson.fromJson(response.body().string(), ImgurSearchResponse.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }
}
