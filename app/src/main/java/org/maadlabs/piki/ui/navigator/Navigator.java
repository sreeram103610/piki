package org.maadlabs.piki.ui.navigator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import org.maadlabs.piki.ui.model.ImageDataModel;
import org.maadlabs.piki.ui.view.intf.TrendingDataViewModel;
import org.maadlabs.piki.ui.view.intf.SearchableViewModel;
import org.maadlabs.piki.ui.view.intf.ImageInfoViewModel;

import javax.inject.Inject;

/**
 * Created by brainfreak on 10/26/17.
 */

public class Navigator {

    @Inject
    SearchableViewModel mSearchableViewModel;

    @Inject
    TrendingDataViewModel mTrendingDataViewModel;

    @Inject
    ImageInfoViewModel mImageInfoViewModel;

    @Inject
    public Navigator() {

    }

    private void replaceFragment(FragmentActivity activity, Fragment fragment, String tag) {
        activity.getSupportFragmentManager().beginTransaction().replace(android.R.id.content, fragment, tag)
                .addToBackStack(null).commit();
    }

    private void addNewFragment(FragmentActivity activity, Fragment fragment, String tag) {
        activity.getSupportFragmentManager().beginTransaction()
                .add(android.R.id.content, fragment, tag).commit();
    }

    private Fragment getFragment(FragmentActivity activity, String tag) {
        FragmentManager manager = activity.getSupportFragmentManager();
        return manager.findFragmentByTag(tag);
    }

    private boolean isVisible(FragmentActivity activity, String tag){

        Fragment fragment = getFragment(activity, tag);
        return fragment != null && fragment.isVisible();

    }

    private void navigateToSearchView(FragmentActivity activity) {

        if (!(mSearchableViewModel instanceof Fragment))
            return;


        String tag = SearchableViewModel.TAG;

        if (!isVisible(activity, tag)) {
            replaceFragment(activity, (Fragment) mSearchableViewModel, tag);
        }
    }

    public void navigateToTrendingView(FragmentActivity activity) {


        if (!(mTrendingDataViewModel instanceof Fragment))
            return;

        String tag = TrendingDataViewModel.TAG;

        if (!isVisible(activity, tag)) {
            if (getFragment(activity, tag) == null) { // Fragment not added in activity. New instance required.
                addNewFragment(activity, (Fragment) mTrendingDataViewModel, tag);
            } else {
                activity.getSupportFragmentManager().popBackStackImmediate(); // Pop once since we are showing searchView for now
            }
        }
    }

    public void navigateToSearchView(FragmentActivity activity, String query) {
        mSearchableViewModel.setQuery(query);
        navigateToSearchView(activity);
    }

    public void navigateToViewImage(FragmentActivity activity, ImageDataModel model) {

        if(!(mImageInfoViewModel instanceof Fragment))
            return;

        String tag = ImageInfoViewModel.TAG;

        mImageInfoViewModel.setImage(model);
        activity.getSupportFragmentManager().beginTransaction().addToBackStack(null)
                .replace(android.R.id.content, (Fragment) mImageInfoViewModel, tag)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
     }
}
