package aiss.videominer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juan C. Alonso
 */
@Entity
@Table(name = "Video")

public class Video {

    @Id
    @JsonProperty("id")
    private String id;

    @Column(name = "name")
    @NotEmpty(message = "Video name cannot be empty")
    @JsonProperty("name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    @JsonProperty("description")
    private String description;

    @Column(name = "releaseTime")
    @NotEmpty(message = "Video release time cannot be empty")
    @JsonProperty("releaseTime")
    private String releaseTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonProperty("user")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "videoId")
    @JsonProperty("comments")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "videoId")
    @JsonProperty("captions")
    private List<Caption> captions = new ArrayList<>();

    public Video() {
    }

    public Video(String name, String description, String releaseTime) {
        this.name = name;
        this.description = description;
        this.releaseTime = releaseTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Caption> getCaptions() {return captions;}

    public void setCaptions(List<Caption> captions) {this.captions = captions;}

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", releaseTime='" + releaseTime + '\'' +
                ", author=" + user +
                ", comments=" + comments +
                ", captions=" + captions +
                '}';
    }
}
