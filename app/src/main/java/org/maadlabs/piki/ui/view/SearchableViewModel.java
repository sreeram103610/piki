package org.maadlabs.piki.ui.view;

/**
 * Created by brainfreak on 10/26/17.
 */

public interface SearchableViewModel extends TrendingDataViewModel {

    String TAG = "SearchableViewModel";

    void setQuery(String query);
}
