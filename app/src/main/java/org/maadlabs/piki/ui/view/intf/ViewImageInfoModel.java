package org.maadlabs.piki.ui.view.intf;

/**
 * Created by brainfreak on 10/31/17.
 */

public interface ViewImageInfoModel<T> extends LoadingInterface {

    String TAG = "ViewImageInfoModel";

    public enum ButtonType {
        DOWNLOAD, SHARE
    }

    void onClick(ButtonType buttonType);
    void setImage(T imageModel);
    void showImage();
}
