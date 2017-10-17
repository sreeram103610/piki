package org.maadlabs.piki.data.di;

import org.maadlabs.piki.data.net.imgur.ImgurAPIClient;

import dagger.Component;

/**
 * Created by brainfreak on 10/16/17.
 */

@Component(modules = ImgurModule.class)
public interface ImgurComponent {

    void inject(ImgurAPIClient client);
}
