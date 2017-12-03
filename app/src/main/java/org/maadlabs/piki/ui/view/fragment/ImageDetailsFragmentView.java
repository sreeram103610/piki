package org.maadlabs.piki.ui.view.fragment;


import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.request.RequestOptions;

import org.maadlabs.piki.R;
import org.maadlabs.piki.security.security.AndroidPermission;
import org.maadlabs.piki.ui.model.ImageDataModel;
import org.maadlabs.piki.ui.presenter.ImageDetailsPresenter;
import org.maadlabs.piki.ui.view.intf.ImageInfoViewModel;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageDetailsFragmentView extends Fragment implements ImageInfoViewModel, View.OnClickListener {

    ImageDataModel mImageData;

    @Inject
    ImageDetailsPresenter mImageDetailsPresenter;

    @Inject
    Context mContext;

    private Set<ActivityCompat.OnRequestPermissionsResultCallback> mOnRequestPermissionsResultCallbacks;

    private View mView;

    @BindView(R.id.image_view)
    ImageView mImageView;
    @BindView(R.id.downloadImageButton)
    ImageButton mDownloadImageButton;
    @BindView(R.id.shareImageButton)
    ImageButton mShareImageButton;
    @Inject
    AndroidPermission mAndroidPermissions;

    @Inject
    public ImageDetailsFragmentView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i("IDFragContext", mContext.hashCode() + "");

        mView = inflater.inflate(R.layout.fragment_image_details, container, false);
        ButterKnife.bind(this, mView);
        mOnRequestPermissionsResultCallbacks = new HashSet<>();
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mImageDetailsPresenter.setView(this);
        mDownloadImageButton.setOnClickListener(this);
        mShareImageButton.setOnClickListener(this);
    }


    @Override
    public void showImage() {
        if (mImageData != null && mImageData.getUri() != null)
            Glide.with(mContext).load(mImageData.getUri()).into(mImageView);
    }

    @Override
    public File getImage() {

        File file = null;
        BitmapDrawable drawable = (BitmapDrawable) mImageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        return file;
    }

    @Override
    public void showDownloadNotification(File file) {

        Intent imageIntent = new Intent();
        imageIntent.setDataAndType(Uri.fromFile(file), "image/*");

        String description = file.getName();

        PendingIntent intent = PendingIntent.getActivity(mContext, 0, imageIntent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notification = new NotificationCompat.Builder(mContext)
                .setContentTitle(getString(R.string.image_downloaded))
                .setContentText(description)
                .setSmallIcon(R.drawable.download_icon_24dp)
                .setAutoCancel(true)
                .setContentIntent(intent);
        notification.build().notify();
    }

    @Override
    public void addStoragePermission(ActivityCompat.OnRequestPermissionsResultCallback callback) {

        if (!mOnRequestPermissionsResultCallbacks.contains(callback))
            mOnRequestPermissionsResultCallbacks.add(callback);
    }

    @Override
    public boolean showRequestPermission() {

        return mAndroidPermissions.requestWriteStoragePermission(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        for (ActivityCompat.OnRequestPermissionsResultCallback callback : mOnRequestPermissionsResultCallbacks) {
            callback.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void hideError() {

    }


    @Override
    public void onClick(ButtonType buttonType) {

        mImageDetailsPresenter.onButtonClicked(buttonType);
    }

    @Override
    public void setImage(ImageDataModel imageModel) {
        mImageData = imageModel;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.shareImageButton:
                onClick(ButtonType.SHARE);
                break;
            case R.id.downloadImageButton:
                onClick(ButtonType.DOWNLOAD);
                break;
        }
    }
}
