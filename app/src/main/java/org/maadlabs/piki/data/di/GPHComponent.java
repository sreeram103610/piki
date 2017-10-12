package org.maadlabs.piki.data.di;

import org.maadlabs.piki.data.net.giphy.GiphyRestApi;

import dagger.Component;

/**
 * Created by brainfreak on 10/10/17.
 */

@Component(modules = GPHModule.class)
public interface GPHComponent {

    void inject(GiphyRestApi api);
}
