package org.maadlabs.piki.data.source;

/**
 * Created by brainfreak on 10/11/17.
 */

public class ImageDataSourceFactory {

    public ImageDataSource createOnlineImageDataSource() {
        return new OnlineDataSource();
    }
}
