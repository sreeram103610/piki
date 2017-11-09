package org.maadlabs.piki.ui.di;


import org.maadlabs.piki.data.di.ApiModule;
import org.maadlabs.piki.data.di.ImageDataRepositoryModule;
import org.maadlabs.piki.ui.view.fragment.ImageDetailsFragmentView;
import org.maadlabs.piki.ui.view.fragment.SearchFragment;
import org.maadlabs.piki.ui.view.fragment.TrendingImagesFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by brainfreak on 10/29/17.
 */

@Singleton
@Component(modules = {MyModule.class, ImageDataRepositoryModule.class, ApiModule.class})
public interface FragmentComponent {

    void inject(SearchFragment fragment);
    void inject(TrendingImagesFragment fragment);
    void inject(ImageDetailsFragmentView fragment);
}
