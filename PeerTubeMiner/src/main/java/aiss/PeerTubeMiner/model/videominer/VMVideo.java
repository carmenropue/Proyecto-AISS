package aiss.PeerTubeMiner.model.videominer;

import java.util.ArrayList;
import java.util.List;

public class VMVideo {

    private String id;
    private String name;
    private String description;
    private String realeaseTime;
    private VMUser author;
    private List<VMComment> comments = new ArrayList<>();
    private List<VMCaption> captions = new ArrayList<>();

    public VMVideo(String id, String name, String description, String realeaseTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.realeaseTime = realeaseTime;
        this.comments = new ArrayList<>();
        this.captions = new ArrayList<>();
    }

    public VMVideo() {
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getRealeaseTime() { return realeaseTime; }

    public void setRealeaseTime(String realeaseTime) { this.realeaseTime = realeaseTime; }

    public VMUser getAuthor() { return author; }
    public void setAuthor(VMUser author) { this.author = author; }

    public List<VMComment> getComments() { return comments; }
    public void setComments(List<VMComment> comments) { this.comments = comments; }

    public List<VMCaption> getCaptions() { return captions; }
    public void setCaptions(List<VMCaption> captions) { this.captions = captions; }
}
