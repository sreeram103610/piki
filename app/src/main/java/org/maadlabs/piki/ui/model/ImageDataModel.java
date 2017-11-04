package org.maadlabs.piki.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by brainfreak on 10/14/17.
 */

public class ImageDataModel implements Parcelable{

    private String uri;

    public ImageDataModel(String uri) {
        this.uri = uri;
    }

    protected ImageDataModel(Parcel in) {
        uri = in.readString();
    }

    public static final Creator<ImageDataModel> CREATOR = new Creator<ImageDataModel>() {
        @Override
        public ImageDataModel createFromParcel(Parcel in) {
            return new ImageDataModel(in);
        }

        @Override
        public ImageDataModel[] newArray(int size) {
            return new ImageDataModel[size];
        }
    };

    public String getUri() {
        return uri;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uri);
    }
}
