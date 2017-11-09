package org.maadlabs.piki.ui.view.intf;

import org.maadlabs.piki.ui.model.ImageDataModel;

/**
 * Created by brainfreak on 10/31/17.
 */

public interface ImageInfoViewModel extends LoadingInterface {

    String TAG = "ViewImageInfoModel";

    public enum ButtonType {
        DOWNLOAD, SHARE
    }

    void onClick(ButtonType buttonType);
    void setImage(ImageDataModel imageModel);
    void showImage();
}
