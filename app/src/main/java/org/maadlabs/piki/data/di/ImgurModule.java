package org.maadlabs.piki.data.di;

import org.maadlabs.piki.data.net.imgur.ImgurAPI;

import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by brainfreak on 10/16/17.
 */

public class ImgurModule {

    private static final String BASE_URL = "https://api.imgur.com/3/gallery";

    @Provides
    Retrofit retrofit() {
        return new Retrofit.Builder().baseUrl(BASE_URL).build();
    }

    @Provides
    ImgurAPI imgurApi(Retrofit retrofit) {
        return retrofit.create(ImgurAPI.class);
    }
}
