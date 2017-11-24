package org.maadlabs.piki.ui.view.intf;

import android.support.v4.app.ActivityCompat;

import org.maadlabs.piki.ui.model.ImageDataModel;

import java.io.File;

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
    File getImage();
    void showDownloadNotification();
    void addStoragePermission(ActivityCompat.OnRequestPermissionsResultCallback callback);
    boolean showRequestPermission();
}
