package org.maadlabs.piki.ui.view;

import org.maadlabs.piki.domain.entity.ImageData;
import org.maadlabs.piki.ui.model.ImageDataModel;

import java.util.List;

/**
 * Created by brainfreak on 10/12/17.
 */

public interface TrendingDataViewModel {

    void onImageClicked(int imageId);
    void loadImages(List<ImageDataModel> imageList);
    void showLoading();
    void hideLoading();
    void showRetry();
    void hideRetry();
    void showError(String errorMessage);

    void init();

    void hideErrorMessage();

    void hideImages();
}
