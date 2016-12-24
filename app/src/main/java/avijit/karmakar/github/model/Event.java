package avijit.karmakar.github.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by USER on 22-12-2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event implements Serializable {

    private String id;
    private String type;
    private Repo repo;
    @JsonProperty("public")
    private boolean isPublic;
    private String created_at;
    private Org org;

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Repo getRepo() {
        return repo;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public String getCreated_at() {
        return created_at;
    }

    public Org getOrg() {
        return org;
    }

}
