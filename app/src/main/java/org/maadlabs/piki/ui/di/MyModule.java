package org.maadlabs.piki.ui.di;

import android.content.Context;

import org.maadlabs.piki.ui.model.ImageDataModel;
import org.maadlabs.piki.ui.view.fragment.ImageDetailsFragment;
import org.maadlabs.piki.ui.view.intf.SearchableViewModel;
import org.maadlabs.piki.ui.view.intf.TrendingDataViewModel;
import org.maadlabs.piki.ui.view.fragment.SearchFragment;
import org.maadlabs.piki.ui.view.fragment.TrendingImagesFragment;
import org.maadlabs.piki.ui.view.intf.ViewImageInfoModel;

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

    @Provides
    ViewImageInfoModel<ImageDataModel> imageInfo() { return new ImageDetailsFragment(); }
}
