package org.maadlabs.piki.data.source;

import javax.inject.Inject;

/**
 * Created by brainfreak on 10/11/17.
 */

public class ImageDataSourceFactory {

    @Inject
    public ImageDataSourceFactory() {

    }
    public ImageDataSource createOnlineImageDataSource() {
        return new OnlineDataSource();
    }
}
