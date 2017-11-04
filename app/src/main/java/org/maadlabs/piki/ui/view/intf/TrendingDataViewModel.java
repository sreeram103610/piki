package org.maadlabs.piki.ui.view.intf;

import org.maadlabs.piki.domain.entity.ImageData;
import org.maadlabs.piki.ui.model.ImageDataModel;

import java.util.List;

/**
 * Created by brainfreak on 10/12/17.
 */

public interface TrendingDataViewModel {

    String TAG = "TrendingDataViewModel";

    void onImageClicked(ImageDataModel model);
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
