package org.maadlabs.piki.data.net.imgur;

import org.maadlabs.piki.data.net.RestApi;
import org.maadlabs.piki.data.net.imgur.model.ImgurImage;
import org.maadlabs.piki.data.net.imgur.model.ImgurSearchRequest;
import org.maadlabs.piki.data.net.imgur.model.ImgurSearchResponse;
import org.maadlabs.piki.data.net.imgur.model.ImgurSearchResponseData;
import org.maadlabs.piki.domain.entity.ImageData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import retrofit2.Retrofit;

/**
 * Created by brainfreak on 10/16/17.
 */

public class ImgurRestApi implements RestApi{

    @Inject
    ImgurAPI mImgurAPI;

    @Inject
    public ImgurRestApi() {
    }


    @Override
    public Observable<List<ImageData>> imageList(String query, int limit) {
        ImgurSearchRequest request = new ImgurSearchRequest.Builder().query(query).build();
        Observable<ImgurSearchResponse> response = mImgurAPI.search(request);
        if (response != null) {
            return response.map(new Function<ImgurSearchResponse, List<ImageData>>() {
                @Override
                public List<ImageData> apply(@NonNull ImgurSearchResponse imgurSearchResponse) throws Exception {
                    List<ImageData> imageDataList = new ArrayList<ImageData>();
                    List<ImgurSearchResponseData> imgurSearchResponseDataList = imgurSearchResponse.getData();

                    for (ImgurSearchResponseData data : imgurSearchResponseDataList) {
                        List<ImgurImage> images = data.getImages();
                        for (ImgurImage imgurImage : images) {
                            ImageData imageData = new ImageData(imgurImage.getImageUrl());
                            imageDataList.add(imageData);
                        }
                    }
                    return imageDataList;
                }
            });
        }
        return null;
    }

    @Override
    public Observable<List<ImageData>> trendingImagesList(int limit) {
        return null;
    }
}
