package org.maadlabs.piki.data.net.imgur.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by brainfreak on 10/16/17.
 */

public class ImgurSearchResponse {

    List<ImgurSearchResponseData> data;
    boolean success;
    int statusCode;

    public List<ImgurSearchResponseData> getData() {
        return data;
    }

    public void setData(List<ImgurSearchResponseData> data) {
        this.data = data;
    }
}
