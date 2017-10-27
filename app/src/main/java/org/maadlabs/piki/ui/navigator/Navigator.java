package org.maadlabs.piki.ui.navigator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import org.maadlabs.piki.ui.view.TrendingDataViewModel;
import org.maadlabs.piki.ui.view.SearchableViewModel;

import javax.inject.Inject;

/**
 * Created by brainfreak on 10/26/17.
 */

public class Navigator {

    @Inject
    SearchableViewModel mSearchableViewModel;

    @Inject
    TrendingDataViewModel mImageDataViewModel;

    @Inject
    public Navigator() {

    }

    private void addNewFragment(FragmentActivity activity, Fragment fragment, String tag) {
        activity.getSupportFragmentManager().beginTransaction()
                .add(fragment, tag).addToBackStack(null).commit();
    }

    private Fragment getCurrentFragment(FragmentActivity activity){

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() == 0)
            return null;
        String fragmentTag = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
        Fragment currentFragment = fragmentManager.findFragmentByTag(fragmentTag);
        return currentFragment;
    }

    private void navigateToSearchView(FragmentActivity activity) {

        if (!(mSearchableViewModel instanceof Fragment))
            return;


        if (!mSearchableViewModel.equals(getCurrentFragment(activity))) {
            String tag = ((Fragment) mSearchableViewModel).getTag();
            addNewFragment(activity, (Fragment) mSearchableViewModel, tag);
        }
    }

    public void navigateToTrendingView(FragmentActivity activity) {

        if (!(mImageDataViewModel instanceof Fragment))
            return;

        if (!mImageDataViewModel.equals(getCurrentFragment(activity))) {
            String tag = ((Fragment) mImageDataViewModel).getTag();
            addNewFragment(activity, (Fragment) mImageDataViewModel, tag);
        }
    }

    public void navigateToSearchView(FragmentActivity activity, String query) {
        mSearchableViewModel.setQuery(query);
        navigateToSearchView(activity);
    }
}
