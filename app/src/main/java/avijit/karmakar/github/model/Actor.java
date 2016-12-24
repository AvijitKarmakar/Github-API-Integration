package avijit.karmakar.github.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by USER on 24-12-2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Actor implements Serializable {

    private long id;
    private String login;
    private String avatar_url;

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

}
