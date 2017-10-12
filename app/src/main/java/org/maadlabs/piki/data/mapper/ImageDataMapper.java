package org.maadlabs.piki.data.mapper;

import com.giphy.sdk.core.models.Image;
import com.giphy.sdk.core.models.Images;
import com.giphy.sdk.core.models.Media;
import com.giphy.sdk.core.network.response.ListMediaResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brainfreak on 10/11/17.
 */

public class ImageDataMapper {

    public List<String> map(ListMediaResponse response) {

        if (response == null)
            return null;

        List<Media> mediaList = response.getData();
        List<String> urlList = new ArrayList<>();

        Images images;
        Image originalImage;
        String url;

        for (Media media : mediaList) {
            images = media.getImages();
            if (images == null)
                continue;
            originalImage = images.getOriginal();
            if (originalImage == null)
                continue;
            url = originalImage.getGifUrl();

            urlList.add(url);
        }
        return urlList;
    }
}
