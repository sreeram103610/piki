package org.maadlabs.piki.ui.di;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import org.maadlabs.piki.ui.view.adapter.ImagesListAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by brainfreak on 10/14/17.
 */

@Module
public class ImagesGridModule {

    Context context;

    public ImagesGridModule(Context context) {
        this.context = context;
    }

    @Provides
    Context context() {
        return context;
    }

}
