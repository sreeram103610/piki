package org.maadlabs.piki.ui;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.maadlabs.piki.R;
import org.maadlabs.piki.data.di.ImageDataRepositoryModule;
import org.maadlabs.piki.ui.di.DaggerImagesGridComponent;
import org.maadlabs.piki.ui.di.ImagesGridModule;
import org.maadlabs.piki.ui.navigator.Navigator;
import org.maadlabs.piki.ui.view.LoadingInterface;
import org.maadlabs.piki.ui.view.fragment.TrendingImagesFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoadingInterface {

    @BindView(R.id.retry_linear_layout)
    LinearLayout mRetryLinearLayout;
    @BindView(R.id.retry_info_message_textview)
    TextView mRetryInfoTextView;
    @BindView(R.id.loading_progress_bar)
    ProgressBar mLoadingProgressBar;
    @Inject
    Navigator mNavigator;

    FragmentManager mFragmentManager;
    TrendingImagesFragment mGridFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        DaggerImagesGridComponent.builder().imageDataRepositoryModule(new ImageDataRepositoryModule())
                .imagesGridModule(new ImagesGridModule(this))
                .build().inject(this);
        mNavigator.navigateToTrendingView(this);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_options_menu, menu);

        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            new Navigator().navigateToSearchView(this, query);
        }
    }
}
