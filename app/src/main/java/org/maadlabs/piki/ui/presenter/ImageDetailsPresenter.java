package org.maadlabs.piki.ui.presenter;

import org.maadlabs.piki.ui.model.ImageDataModel;
import org.maadlabs.piki.ui.view.intf.ImageInfoViewModel;

import javax.inject.Inject;

/**
 * Created by brainfreak on 10/31/17.
 */

public class ImageDetailsPresenter implements Presenter<ImageInfoViewModel> {

    ImageInfoViewModel mImageInfoViewModel;

    @Inject
    public ImageDetailsPresenter() {
    }

    @Override
    public void setView(ImageInfoViewModel view) {
        mImageInfoViewModel = view;
        loadView();
    }

    private void loadView() {
        mImageInfoViewModel.showImage();
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

    public void onButtonClicked(ImageInfoViewModel.ButtonType buttonType) {
        switch (buttonType) {
            case DOWNLOAD:
                mImageInfoViewModel.getImage();
        }
    }
}
