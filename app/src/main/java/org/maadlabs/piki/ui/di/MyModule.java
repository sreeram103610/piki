package org.maadlabs.piki.ui.di;

import android.content.Context;

import org.maadlabs.piki.ui.model.ImageDataModel;
import org.maadlabs.piki.ui.navigator.Navigator;
import org.maadlabs.piki.ui.view.fragment.ImageDetailsFragmentView;
import org.maadlabs.piki.ui.view.intf.SearchableViewModel;
import org.maadlabs.piki.ui.view.intf.TrendingDataViewModel;
import org.maadlabs.piki.ui.view.fragment.SearchFragment;
import org.maadlabs.piki.ui.view.fragment.TrendingImagesFragment;
import org.maadlabs.piki.ui.view.intf.ImageInfoViewModel;

import javax.inject.Named;

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
    TrendingDataViewModel trending(TrendingImagesFragment fragment) {
        return fragment;
    }

    @Provides
    SearchableViewModel searchable(SearchFragment fragment) {
        return fragment;
    }

    @Provides
    ImageInfoViewModel imageInfo(ImageDetailsFragmentView fragmentView) { return fragmentView; }
}
