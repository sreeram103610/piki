package org.maadlabs.piki.data.net.imgur.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by brainfreak on 10/16/17.
 */

public class ImgurImage {

    String type;
    @SerializedName("link")
    String linkUrl;
    @SerializedName("mp4")
    String mp4Url;
    @SerializedName("gifv")
    String gifvUrl;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getMp4Url() {
        return mp4Url;
    }

    public void setMp4Url(String mp4Url) {
        this.mp4Url = mp4Url;
    }

    public String getGifvUrl() {
        return gifvUrl;
    }

    public void setGifvUrl(String gifvUrl) {
        this.gifvUrl = gifvUrl;
    }

    public String getImageUrl() {
        String url;

        if (type.contains("jpeg")) {
            url = linkUrl;
        } else if (type.contains("mp4")) {
            url = mp4Url;
        } else if (type.contains("gif")) {
            url = gifvUrl;
            if(gifvUrl.endsWith(".gifv"))   // Remove v from the end
                url = gifvUrl.substring(0, gifvUrl.length() - 1);
        } else {
            url = linkUrl;
        }
        return url != null ? url : linkUrl;
    }
}
