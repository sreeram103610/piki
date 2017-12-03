package org.maadlabs.piki.ui.view.intf;

import org.maadlabs.piki.ui.model.ImageDataModel;

import java.util.List;

/**
 * Created by brainfreak on 10/26/17.
 */

public interface SearchableViewModel {

    String TAG = "SearchableViewModel";

    void setQuery(String query);
    void onImageClicked(ImageDataModel model);
    void loadImages(List<ImageDataModel> imageList);
    void showLoading();
    void hideLoading();
    void showRetry();
    void hideRetry();
    void showError(String errorMessage);
    void showError(int error);
    void init();

    void hideErrorMessage();

    void hideImages();

}
