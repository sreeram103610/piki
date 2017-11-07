package org.maadlabs.piki.ui.presenter;

import org.maadlabs.piki.ui.view.intf.ViewImageInfoModel;

import javax.inject.Inject;

/**
 * Created by brainfreak on 10/31/17.
 */

public class ImageDetailsPresenter implements Presenter<ViewImageInfoModel> {

    @Inject
    ViewImageInfoModel mViewImageInfoModel;

    @Override
    public void setView(ViewImageInfoModel view) {
        mViewImageInfoModel = view;
        loadView();
    }

    private void loadView() {
        mViewImageInfoModel.showImage();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void destory() {

    }
}
