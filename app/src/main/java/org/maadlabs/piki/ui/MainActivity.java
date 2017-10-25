package org.maadlabs.piki.ui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.maadlabs.piki.R;
import org.maadlabs.piki.ui.view.LoadingInterface;
import org.maadlabs.piki.ui.view.fragment.ImagesGridFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoadingInterface {

    @BindView(R.id.retry_linear_layout)
    LinearLayout mRetryLinearLayout;
    @BindView(R.id.retry_info_message_textview)
    TextView mRetryInfoTextView;
    @BindView(R.id.loading_progress_bar)
    ProgressBar mLoadingProgressBar;

    FragmentManager mFragmentManager;
    ImagesGridFragment mGridFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mFragmentManager = getSupportFragmentManager();
        if ((mGridFragment = (ImagesGridFragment) mFragmentManager.findFragmentByTag(ImagesGridFragment.TAG)) == null) {
            mGridFragment = new ImagesGridFragment();
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, mGridFragment, ImagesGridFragment.TAG).commit();
        }
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
}
