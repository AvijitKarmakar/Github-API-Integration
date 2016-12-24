package avijit.karmakar.github.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by USER on 22-12-2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Repo implements Serializable {

    private long id;
    private String name;
    private String url;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

}
