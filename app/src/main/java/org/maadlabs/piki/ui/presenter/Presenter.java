package org.maadlabs.piki.ui.presenter;

import android.view.View;

/**
 * Created by brainfreak on 10/12/17.
 */

public interface Presenter<T> {

    void setView(T view);
    void pause();
    void resume();
    void destory();
}
