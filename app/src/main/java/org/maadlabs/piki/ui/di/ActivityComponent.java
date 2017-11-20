package org.maadlabs.piki.ui.di;

import android.app.Activity;

import org.maadlabs.piki.data.di.ApiModule;
import org.maadlabs.piki.data.di.ImageDataRepositoryModule;
import org.maadlabs.piki.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by brainfreak on 10/29/17.
 */

@Component(modules = {ApiModule.class, MyModule.class, ImageDataRepositoryModule.class})
public interface ActivityComponent {

    void inject(MainActivity activity);
}
