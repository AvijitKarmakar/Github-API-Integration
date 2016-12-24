package avijit.karmakar.github.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by USER on 22-12-2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class RepoDetail implements Serializable {

    private long id;
    private String name;
    private String full_name;
    private Owner owner;
    @JsonProperty("private")
    private boolean isPrivate;
    private String description;
    private boolean fork;
    private String created_at;
    private String updated_at;
    private String pushed_at;
    private String git_url;
    private String clone_url;
    private long stargazers_count;
    private long watchers_count;
    private String language;
    private boolean has_issues;
    private boolean has_downloads;
    private long forks_count;
    private long open_issues_count;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFull_name() {
        return full_name;
    }

    public Owner getOwner() {
        return owner;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public String getDescription() {
        return description;
    }

    public boolean isFork() {
        return fork;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getPushed_at() {
        return pushed_at;
    }

    public String getGit_url() {
        return git_url;
    }

    public String getClone_url() {
        return clone_url;
    }

    public long getStargazers_count() {
        return stargazers_count;
    }

    public long getWatchers_count() {
        return watchers_count;
    }

    public String getLanguage() {
        return language;
    }

    public boolean isHas_issues() {
        return has_issues;
    }

    public boolean isHas_downloads() {
        return has_downloads;
    }

    public long getForks_count() {
        return forks_count;
    }

    public long getOpen_issues_count() {
        return open_issues_count;
    }

}
