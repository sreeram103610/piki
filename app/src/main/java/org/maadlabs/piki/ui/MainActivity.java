package org.maadlabs.piki.ui;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.maadlabs.piki.R;
import org.maadlabs.piki.ui.di.DaggerActivityComponent;
import org.maadlabs.piki.ui.di.MyModule;
import org.maadlabs.piki.ui.navigator.Navigator;
import org.maadlabs.piki.ui.view.intf.LoadingInterface;
import org.maadlabs.piki.ui.view.fragment.TrendingImagesFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoadingInterface, SearchView.OnQueryTextListener, MenuItemCompat.OnActionExpandListener {

    @BindView(R.id.retry_linear_layout)
    LinearLayout mRetryLinearLayout;
    @BindView(R.id.retry_info_message_textview)
    TextView mRetryInfoTextView;
    @BindView(R.id.loading_progress_bar)
    ProgressBar mLoadingProgressBar;
    @Inject
    Navigator mNavigator;
    SearchView mSearchView;

    FragmentManager mFragmentManager;
    TrendingImagesFragment mGridFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        DaggerActivityComponent.builder()
                .myModule(new MyModule(this)).build().inject(this);
        initToolbar();
        mNavigator.navigateToTrendingView(this);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void showLoading() {
        mLoadingProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mLoadingProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showRetry() {
        mRetryLinearLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        mRetryLinearLayout.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        mRetryInfoTextView.setText(message);
        mRetryLinearLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideError() {
        mRetryInfoTextView.setVisibility(View.GONE);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_options_menu, menu);

        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView = (SearchView) menu.findItem(R.id.search).getActionView();
        mSearchView.setSearchableInfo(manager.getSearchableInfo(new ComponentName(getApplicationContext(), MainActivity.class)));
        mSearchView.setOnQueryTextListener(this);

        MenuItem searchMenuItem = menu.findItem(R.id.search);
        MenuItemCompat.setOnActionExpandListener(searchMenuItem, this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.i("onQuery2", "Search");
        mNavigator.navigateToSearchView(this, query);
        mSearchView.clearFocus();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        mNavigator.navigateToTrendingView(MainActivity.this);
        return true;
    }
}
