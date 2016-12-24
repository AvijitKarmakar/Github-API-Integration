package avijit.karmakar.github.restservice;

import java.util.List;

import avijit.karmakar.github.model.Event;
import avijit.karmakar.github.model.RepoDetail;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by USER on 22-12-2016.
 */

public class GithubRestService {

    private GithubRestInterface githubRestInterface;

    public GithubRestService() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
//        if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(httpLoggingInterceptor);
//        }
        githubRestInterface = new Retrofit.Builder()
                .baseUrl(GithubRestInterface.BASE_URL)
                .client(clientBuilder.build())
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(GithubRestInterface.class);
    }

    /**
     * This method is used to fetch user's events
     * @param username whose events data we have to fetch
     * @param callback This is the callback of api call
     */
    public void getUserEvents(String username, Callback<List<Event>> callback) {
        githubRestInterface.getEvents(username).enqueue(callback);
    }

    /**
     * This method is used to fetch user's repo details
     * @param username whose repo detail we have to fetch
     * @param reponame This repo's data we have to fetch
     * @param callback This is the callback of api call
     */
    public void getRepoDetails(String username, String reponame, Callback<RepoDetail> callback) {
        githubRestInterface.getRepoDetails(username, reponame).enqueue(callback);
    }

}
