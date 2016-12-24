package avijit.karmakar.github.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by USER on 22-12-2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Org implements Serializable {

    private long id;
    private String login;
    private String gravatar_id;
    private String url;
    private String avatar_url;

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getGravatar_id() {
        return gravatar_id;
    }

    public String getUrl() {
        return url;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

}
