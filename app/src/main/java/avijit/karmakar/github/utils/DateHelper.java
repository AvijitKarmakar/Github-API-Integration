package avijit.karmakar.github.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by USER on 23-12-2016.
 */

public class DateHelper {

    /**
     * This method is used to format date from yyyy-MM-dd'T'HH:mm:ss'Z' to dd-MM-yyyy HH:mm
     * @param creationDate yyyy-MM-dd'T'HH:mm:ss'Z' type of date formats
     * @return dd-MM-yyyy HH:mm formatted date
     */
    public static String formatDateIsoToddMMyyyyHHmm(String creationDate) {
        SimpleDateFormat sourceDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",
                Locale.getDefault());
        SimpleDateFormat destDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault());
        Date date = null;
        try {
            date = sourceDateFormat.parse(creationDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return destDateFormat.format(date);
    }

}
