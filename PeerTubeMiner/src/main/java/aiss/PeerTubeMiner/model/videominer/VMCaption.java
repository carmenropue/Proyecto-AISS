package aiss.PeerTubeMiner.model.videominer;

public class VMCaption {

    //id string, link string, language string
    private String id;
    private String link;
    private String language;

    public VMCaption(String id, String link, String language) {
        this.id = id;
        this.link = link;
        this.language = language;
    }

    public VMCaption() {
    }

    public String getId() { return id; }

    public void setId(String id) {
        this.id = id; }

    public String getLink() { return link; }

    public void setLink(String link) {
        this.link = link; }

    public String getLanguage() { return language; }

    public void setLanguage(String language) {
        this.language = language;
    }
}
