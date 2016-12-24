package avijit.karmakar.github.dependencyinjection;

import android.app.Activity;
import android.content.Context;

import avijit.karmakar.github.datastore.OrgImgStore;
import avijit.karmakar.github.restservice.GithubRestService;
import dagger.Module;
import dagger.Provides;

/**
 * Created by USER on 22-12-2016.
 */

@Module
public class ApplicationModule {

    private Activity activity;

    public ApplicationModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    GithubRestService getGithubRestService() {
        return new GithubRestService();
    }

    @Provides
    OrgImgStore getOrgImgStore() {
        return new OrgImgStore(activity);
    }

}
