package org.maadlabs.piki.data.net.imgur.model;

import java.util.List;

/**
 * Created by brainfreak on 10/16/17.
 */

public class ImgurSearchResponseData {

    List<ImgurImage> images;

    public List<ImgurImage> getImages() {
        return images;
    }

    public void setImages(List<ImgurImage> images) {
        this.images = images;
    }
}
