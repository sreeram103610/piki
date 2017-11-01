package org.maadlabs.piki.ui.view.intf;

/**
 * Created by brainfreak on 10/31/17.
 */

public interface ViewImageInfoModel extends LoadingInterface {

    public enum ButtonType {
        DOWNLOAD, SHARE
    }

    void onClick(ButtonType buttonType);

}
