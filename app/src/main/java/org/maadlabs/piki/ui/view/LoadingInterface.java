package org.maadlabs.piki.ui.view;

/**
 * Created by brainfreak on 10/14/17.
 */

public interface LoadingInterface {

    void showLoading();
    void hideLoading();
    void showRetry();
    void hideRetry();
    void showError(String message);

    void hideError();
}
