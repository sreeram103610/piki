package org.maadlabs.piki.data.net.imgur.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by brainfreak on 10/16/17.
 */

class ImgurImage {

    String type;
    @SerializedName("link")
    String linkUrl;
    @SerializedName("mp4")
    String mp4Url;
    @SerializedName("gifv")
    String gifvUrl;
}
