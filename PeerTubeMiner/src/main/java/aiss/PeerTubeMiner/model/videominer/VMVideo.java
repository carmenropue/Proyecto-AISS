package aiss.PeerTubeMiner.model.videominer;

public class VMVideo {

    private String id;
    private String name;
    private String description;
    private String realeaseTime;

    public VMVideo(String id, String name, String description, String realeaseTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.realeaseTime = realeaseTime;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getRealeaseTime() { return realeaseTime; }

    public void setRealeaseTime(String realeaseTime) { this.realeaseTime = realeaseTime; }
}
