package org.maadlabs.piki.security.security;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import javax.inject.Inject;

/**
 * Created by brainfreak on 11/24/17.
 */

public class MyPermissions implements AndroidPermission {

    @Inject
    public MyPermissions() {
    }

    @Override
    public boolean requestWriteStoragePermission(Fragment fragment) {

        if (ContextCompat.checkSelfPermission(fragment.getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            fragment.requestPermissions(
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    AndroidPermission.WRITE_STORAGE_REQUEST);
            return false;
        }
        return true;
    }
}
