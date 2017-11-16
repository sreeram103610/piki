package org.maadlabs.piki.data.util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;

import javax.inject.Inject;

/**
 * Created by brainfreak on 11/11/17.
 */

public class FileUtils {

    @Inject
    Context mContext;

    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    public File getAlbumStorageDir(String name) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), name);

        return file;
    }

    public boolean storeFile(String fileName, String directoryPath) {
        if (!(fileName != null && directoryPath != null && fileName.trim().length() > 0 && directoryPath.trim().length() > 0))
            return false;

        File file = new File(directoryPath + "/" + fileName);

        if (!file.mkdirs()) {

        }
        return false;

    }
}

