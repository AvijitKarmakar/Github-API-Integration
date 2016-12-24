package avijit.karmakar.github.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by USER on 22-12-2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Owner implements Serializable {

    private String login;
    private long id;
    private String avatar_url;
    private String type;
    private boolean site_admin;

    public String getLogin() {
        return login;
    }

    public long getId() {
        return id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getType() {
        return type;
    }

    public boolean isSite_admin() {
        return site_admin;
    }

}
