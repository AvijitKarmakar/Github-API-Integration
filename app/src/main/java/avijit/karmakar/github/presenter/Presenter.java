package avijit.karmakar.github.presenter;

/**
 * Created by USER on 22-12-2016.
 */

public interface Presenter {

    /**
     * This method is called on activity's onPostCreate method
     */
    void create();

    /**
     * This method is called on activity's onPostResume method
     */
    void resume();

    /**
     * This method is called on activity's onPause method
     */
    void pause();

    /**
     * This method is called on activity's onDestroy method
     */
    void destroy();

}
