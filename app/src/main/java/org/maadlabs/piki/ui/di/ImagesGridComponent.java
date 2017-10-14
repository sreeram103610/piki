package org.maadlabs.piki.ui.di;

import org.maadlabs.piki.ui.view.fragment.ImagesGridFragment;

import dagger.Component;

/**
 * Created by brainfreak on 10/14/17.
 */

@Component(modules = ImagesGridModule.class)
public interface ImagesGridComponent {

    void inject(ImagesGridFragment fragment);
}
