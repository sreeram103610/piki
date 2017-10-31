package org.maadlabs.piki.ui.di;

import android.content.Context;

import org.maadlabs.piki.domain.interacter.TrendingImagesUseCase;
import org.maadlabs.piki.ui.mapper.ImageDataModelMapper;
import org.maadlabs.piki.ui.presenter.ImageDetailsPresenter;
import org.maadlabs.piki.ui.view.SearchableViewModel;
import org.maadlabs.piki.ui.view.TrendingDataViewModel;
import org.maadlabs.piki.ui.view.fragment.SearchFragment;
import org.maadlabs.piki.ui.view.fragment.TrendingImagesFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by brainfreak on 10/14/17.
 */

@Module
public class MyModule {

    Context context;

    public MyModule(Context context) {
        this.context = context;
    }

    @Provides
    Context context() {
        return context;
    }

    @Provides
    TrendingDataViewModel trending() {
        return new TrendingImagesFragment();
    }

    @Provides
    SearchableViewModel searchable() {
        return new SearchFragment();
    }

}