package aiss.DailyMotionMiner.model.videominer;

public class VMChannel {

    private String id;
    private String name;
    private String description;
    private String createdTime;

    public VMChannel(String id, String name, String description, String createdTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdTime = createdTime;
    }

    public String getId() { return id; }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedTime() { return createdTime; }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}
