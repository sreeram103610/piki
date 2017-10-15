package org.maadlabs.piki.data.di;

import com.giphy.sdk.core.network.api.GPHApiClient;

import org.maadlabs.piki.data.net.RestApi;
import org.maadlabs.piki.data.net.giphy.GPHApiSync;
import org.maadlabs.piki.data.net.giphy.GPHApiSyncClient;
import org.maadlabs.piki.data.net.giphy.GiphyRestApi;

import dagger.Module;
import dagger.Provides;

/**
 * Created by brainfreak on 10/10/17.
 */

@Module
public class GPHModule {

    String API_KEY = "aA9LxtTbUJTfx9ujghPgaJLhZP9sW8uw";

    @Provides
    GPHApiSync client() {
        return new GPHApiSyncClient(API_KEY);
    }

}
