package org.maadlabs.piki.data.di;

import org.maadlabs.piki.data.net.RestApi;
import org.maadlabs.piki.data.net.giphy.GiphyRestApi;
import org.maadlabs.piki.data.net.imgur.ImgurRestApi;
import org.maadlabs.piki.data.repository.ImageDataRepository;
import org.maadlabs.piki.data.source.ImageDataSource;
import org.maadlabs.piki.data.source.ImageDataSourceFactory;
import org.maadlabs.piki.data.source.OnlineDataSource;
import org.maadlabs.piki.domain.repository.ImageRepository;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by brainfreak on 10/19/17.
 */

@Module
public class ImageDataRepositoryModule {

    @Provides
    ImageDataSource imageSource(OnlineDataSource source) { return source; }

    @Provides
    ImageRepository imageRepository(ImageDataRepository repository) { return repository; }

    @Provides
    List<RestApi> restApis(GiphyRestApi giphyRestApi, ImgurRestApi imgurRestApi) {

        List<RestApi> apiList = new ArrayList<>();
        apiList.add(giphyRestApi);
        apiList.add(imgurRestApi);
        return apiList;
    }
}
