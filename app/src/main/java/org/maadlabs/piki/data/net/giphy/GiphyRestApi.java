package org.maadlabs.piki.data.net.giphy;

import com.giphy.sdk.core.models.Image;
import com.giphy.sdk.core.models.Images;
import com.giphy.sdk.core.models.Media;
import com.giphy.sdk.core.models.enums.MediaType;
import com.giphy.sdk.core.network.response.ListMediaResponse;

import org.maadlabs.piki.data.di.DaggerGPHComponent;
import org.maadlabs.piki.domain.entity.ImageData;
import org.maadlabs.piki.data.net.RestApi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by brainfreak on 10/10/17.
 */

public class GiphyRestApi implements RestApi {

    @Inject
    GPHApiSync mGPHApi;
    ListMediaResponse mListMediaResponse;

    @Inject
    public GiphyRestApi() {
    }

    @Override
    public Observable<List<ImageData>> imageList(final String query, final int limit) {

        return Observable.fromCallable(new Callable<ListMediaResponse>() {

            @Override
            public ListMediaResponse call() throws Exception {
                return mGPHApi.search(query, MediaType.gif, limit, null, null, null);

            }
        }).map(new Function<ListMediaResponse, List<ImageData>>() {
                @Override
                public List<ImageData> apply(@NonNull ListMediaResponse response) throws Exception {
                    return map(response);
                }
            });

    }

    private List<ImageData> map(ListMediaResponse response) {

        if (response == null)
            return null;

        List<Media> mediaList = response.getData();
        List<ImageData> urlList = new ArrayList<>();

        Images images;
        Image originalImage;
        ImageData imageData;

        for (Media media : mediaList) {
            images = media.getImages();
            if (images == null)
                continue;
            originalImage = images.getOriginal();
            if (originalImage == null)
                continue;
            imageData = new ImageData(originalImage.getGifUrl());

            urlList.add(imageData);
        }
        return urlList;
    }
}
