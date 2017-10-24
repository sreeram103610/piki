package org.maadlabs.piki.ui.di;

import org.maadlabs.piki.data.di.ApiModule;
import org.maadlabs.piki.data.di.ImageDataRepositoryModule;
import org.maadlabs.piki.ui.view.fragment.ImagesGridFragment;

import dagger.Component;

/**
 * Created by brainfreak on 10/14/17.
 */

@Component(modules = {ImagesGridModule.class, ApiModule.class, ImageDataRepositoryModule.class})
public interface ImagesGridComponent {

    void inject(ImagesGridFragment fragment);
}
