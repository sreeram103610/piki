package org.maadlabs.piki.security.security;

import android.support.v4.app.Fragment;

/**
 * Created by brainfreak on 11/24/17.
 */

public interface AndroidPermission {

    public static final int WRITE_STORAGE_REQUEST = 1;
    boolean requestWriteStoragePermission(Fragment fragment);
}
