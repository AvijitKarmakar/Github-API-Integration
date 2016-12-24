package avijit.karmakar.github.datastore;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by USER on 23-12-2016.
 */

public class OrgImgStore {

    private Context context;

    public OrgImgStore(Context context) {
        this.context = context;
    }

    /**
     * This method is used to save bitmap in file
     * @param orgImgBitmap which will be stored in file
     * @param fileName This is the filename where this bitmap will be stored
     */
    public void saveOrgImg(final Bitmap orgImgBitmap, String fileName) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            context.openFileOutput(fileName, Context.MODE_PRIVATE);
            orgImgBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to fetch data from file
     * @param fileName This is the filename where data is stored
     * @return It returns the bitmap stored in the file
     */
    public Bitmap getOrgImg(String fileName) {
        try {
            return BitmapFactory.decodeStream(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method is used to delete file
     * @param fileName his is the filename where data is stored
     */
    public void removeOrgImg(String fileName) {
        context.deleteFile(fileName);
    }

}
