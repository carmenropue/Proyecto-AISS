
package aiss.PeerTubeMiner.model.peerTube;

import java.util.List;
import javax.annotation.processing.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Video {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("publishedAt") //realeaseTime
    private String publishedAt;
    @JsonProperty("originallyPublishedAt")//realeaseTime¿¿
    private String originallyPublishedAt;
    @JsonProperty("truncatedDescription")//description¿¿
    private String truncatedDescription;
    @JsonProperty("account")
    private User account;
    @JsonProperty("channel")
    private Channel channel;
    //comments
    //captions


    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("publishedAt")
    public String getPublishedAt() {
        return publishedAt;
    }

    @JsonProperty("publishedAt")
    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    @JsonProperty("originallyPublishedAt")
    public String getOriginallyPublishedAt() {
        return originallyPublishedAt;
    }

    @JsonProperty("originallyPublishedAt")
    public void setOriginallyPublishedAt(String originallyPublishedAt) {
        this.originallyPublishedAt = originallyPublishedAt;
    }

    @JsonProperty("truncatedDescription")
    public String getTruncatedDescription() {
        return truncatedDescription;
    }

    @JsonProperty("truncatedDescription")
    public void setTruncatedDescription(String truncatedDescription) {
        this.truncatedDescription = truncatedDescription;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    //@JsonProperty("comments")
    //public Integer getComments() {return comments;}

    //@JsonProperty("comments")
    //public void setComments(Integer comments) {this.comments = comments;}

    @JsonProperty("account")
    public User getAccount() {
        return account;
    }

    @JsonProperty("account")
    public void setAccount(User account) {
        this.account = account;
    }

    @JsonProperty("channel")
    public Channel getChannel() {
        return channel;
    }

    @JsonProperty("channel")
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Video.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("publishedAt");
        sb.append('=');
        sb.append(((this.publishedAt == null)?"<null>":this.publishedAt));
        sb.append(',');
        sb.append("truncatedDescription");
        sb.append('=');
        sb.append(((this.truncatedDescription == null)?"<null>":this.truncatedDescription));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("account");
        sb.append('=');
        sb.append(((this.account == null)?"<null>":this.account));
        sb.append(',');
        sb.append("channel");
        sb.append('=');
        sb.append(((this.channel == null)?"<null>":this.channel));
        sb.append(',');
//        sb.append("comments");
//        sb.append('=');
//        sb.append(((this.comments == null)?"<null>":this.comments));
//        sb.append(',');
//        sb.append("captions");
//        sb.append('=');
//        sb.append(((this.captions == null)?"<null>":this.captions));
//        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
