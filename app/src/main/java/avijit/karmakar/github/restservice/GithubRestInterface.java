package avijit.karmakar.github.restservice;

import java.util.List;

import avijit.karmakar.github.model.Event;
import avijit.karmakar.github.model.RepoDetail;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by USER on 22-12-2016.
 */

interface GithubRestInterface {

    String BASE_URL = "https://api.github.com/";

    @GET("users/{username}/events")
    Call<List<Event>> getEvents(@Path("username") String username);

    @GET("repos/{username}/{reponame}")
    Call<RepoDetail> getRepoDetails(@Path("username") String username,
                                    @Path("reponame") String reponame);

}
