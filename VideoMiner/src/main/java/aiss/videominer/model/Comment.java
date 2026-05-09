package aiss.videominer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

/**
 * @author Juan C. Alonso
 */
@Entity
@Table(name = "Comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @Column(name = "text", columnDefinition = "TEXT")
    @NotEmpty(message = "Comment text cannot be empty")
    @JsonProperty("text")
    private String text;

    @Column(name = "createdOn")
    @NotEmpty(message = "Comment creation date cannot be empty")
    @JsonProperty("createdOn")
    private String createdOn;

    public Comment() {
    }

    public Comment(String text, String createdOn) {
        this.text = text;
        this.createdOn = createdOn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }


    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", createdOn='" + createdOn + '\'' +
                '}';
    }
}
