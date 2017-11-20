package org.maadlabs.piki.data.util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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

    public static File storeFile(String fileName, String directoryPath, File srcFile) {
        if (!(fileName != null && directoryPath != null && fileName.trim().length() > 0 && directoryPath.trim().length() > 0
                && srcFile != null))
            return null;

        if (!isExternalStorageWritable())
            return null;

        File destFile = new File(directoryPath + "/" + fileName);

        if (!destFile.mkdirs()) {

            try {
                copyFileUsingStream(srcFile, destFile);
                return srcFile;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;

    }

    private static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }
}

