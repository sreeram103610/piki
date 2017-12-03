package org.maadlabs.piki.ui;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.support.annotation.NonNull;
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
import org.maadlabs.piki.ui.view.intf.ToolbarCallback;
import org.maadlabs.piki.ui.view.intf.LoadingInterface;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoadingInterface, ToolbarCallback, SearchView.OnQueryTextListener, MenuItemCompat.OnActionExpandListener {

    public static final String SEARCH_VIEW_KEY = "search_view_key";

    @BindView(R.id.retry_linear_layout)
    LinearLayout mRetryLinearLayout;
    @BindView(R.id.retry_info_message_textview)
    TextView mRetryInfoTextView;
    @BindView(R.id.loading_progress_bar)
    ProgressBar mLoadingProgressBar;

    @Inject
    Navigator mNavigator;

    SearchView mSearchView;
    private MenuItem mSearchMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        DaggerActivityComponent.builder()
                .myModule(new MyModule(getBaseContext())).build().inject(this);
        initToolbar();
        mNavigator.navigateToTrendingView(this);
        Log.i("ActivityContext", getBaseContext().hashCode() + "");
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

        mSearchMenuItem = menu.findItem(R.id.search);

        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView = (SearchView) menu.findItem(R.id.search).getActionView();
        mSearchView.setSearchableInfo(manager.getSearchableInfo(new ComponentName(getApplicationContext(), MainActivity.class)));
        mSearchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(mSearchMenuItem, this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
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
        Log.i("on", "expand");
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        Log.i("on", "collapse");
        onBackPressed();
        boolean collapseFragment = mNavigator.onImageDetailsViewVisible(this);
        return !collapseFragment;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            //Title bar back press triggers onBackPressed()
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        boolean goBack = mNavigator.onBackPressed(this);
        if (goBack && !mSearchMenuItem.isActionViewExpanded()) {
            super.onBackPressed();
        }
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

    }

    @Override
    public Bundle onSaveToolbarData() {

        Bundle bundle = new Bundle();
        if (mSearchView != null && mSearchView.getQuery() != null && mSearchView.getQuery().toString().length() > 0)
            bundle.putString(SEARCH_VIEW_KEY, mSearchView.getQuery().toString());
        return bundle;
    }

    @Override
    public void onRestoreToolbarData(Bundle bundle) {

        if (bundle != null && bundle.containsKey(SEARCH_VIEW_KEY)) {
            String value = bundle.getString(SEARCH_VIEW_KEY);
            if (value == null || value.length() == 0 || mSearchView == null)
                return;
            mSearchView.setQuery(value, false);
            mSearchMenuItem.expandActionView();
        }
    }

}
