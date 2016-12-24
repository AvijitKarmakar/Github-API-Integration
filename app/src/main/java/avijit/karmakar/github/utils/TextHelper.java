package avijit.karmakar.github.utils;

/**
 * Created by USER on 23-12-2016.
 */

public class TextHelper {

    public static String splitOnSplash(String repoName) {
        String[] names = repoName.split("/");
        return names[1];
    }

}
