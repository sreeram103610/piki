package org.maadlabs.piki.ui.presenter;

import org.maadlabs.piki.ui.view.intf.TrendingDataViewModel;

/**
 * Created by brainfreak on 10/31/17.
 */

public class ImageDetailsPresenter implements Presenter<TrendingDataViewModel> {

    TrendingDataViewModel mTrendingDataViewModel;

    @Override
    public void setView(TrendingDataViewModel view) {
        mTrendingDataViewModel = view;
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
