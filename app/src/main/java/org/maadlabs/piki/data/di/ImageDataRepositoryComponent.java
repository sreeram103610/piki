package org.maadlabs.piki.data.di;

import org.maadlabs.piki.data.repository.ImageDataRepository;

import dagger.Component;

/**
 * Created by brainfreak on 10/19/17.
 */

@Component(modules = ImageDataRepositoryModule.class)
public interface ImageDataRepositoryComponent {

    void inject(ImageDataRepository imageDataRepository);
}
