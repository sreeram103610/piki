package org.maadlabs.piki.data.di;

import com.giphy.sdk.core.network.api.GPHApiClient;
import com.google.gson.Gson;

import org.maadlabs.piki.data.net.RestApi;
import org.maadlabs.piki.data.net.giphy.GPHApiSync;
import org.maadlabs.piki.data.net.giphy.GPHApiSyncClient;
import org.maadlabs.piki.data.net.giphy.GiphyRestApi;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by brainfreak on 10/10/17.
 */

@Module
public class ApiModule {

    String API_KEY = "aA9LxtTbUJTfx9ujghPgaJLhZP9sW8uw";

    @Provides
    @Named("imgur_base_url")
    static String imgurUrl() { return "https://api.imgur.com/3/gallery"; }

    @Provides
    GPHApiSync client() {
        GPHApiSyncClient client = new GPHApiSyncClient(API_KEY);
        return client;
    }

    @Provides
    OkHttpClient okhttpClient() {
        OkHttpClient client = new OkHttpClient();
        return client;
    }

    @Provides
    Gson gson() {
        Gson gson = new Gson();
        return gson;
    }
}
