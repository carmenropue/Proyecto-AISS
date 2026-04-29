package aiss.DailyMotionMiner.model;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "title",
        "description",
        "created_time",
        "tags",
        "owner.id",
        "owner.screenname",
        "owner.url",
        "owner.avatar_120_url"
})
@Generated("jsonschema2pojo")
public class VideoItem {

    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("created_time")
    private Integer createdTime;
    @JsonProperty("tags")
    private java.util.List<Object> tags;
    @JsonProperty("owner.id")
    private String ownerId;
    @JsonProperty("owner.screenname")
    private String ownerScreenname;
    @JsonProperty("owner.url")
    private String ownerUrl;
    @JsonProperty("owner.avatar_120_url")
    private String ownerAvatar120Url;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("created_time")
    public Integer getCreatedTime() {
        return createdTime;
    }

    @JsonProperty("created_time")
    public void setCreatedTime(Integer createdTime) {
        this.createdTime = createdTime;
    }

    @JsonProperty("tags")
    public java.util.List<Object> getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(java.util.List<Object> tags) {
        this.tags = tags;
    }

    @JsonProperty("owner.id")
    public String getOwnerId() {
        return ownerId;
    }

    @JsonProperty("owner.id")
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @JsonProperty("owner.screenname")
    public String getOwnerScreenname() {
        return ownerScreenname;
    }

    @JsonProperty("owner.screenname")
    public void setOwnerScreenname(String ownerScreenname) {
        this.ownerScreenname = ownerScreenname;
    }

    @JsonProperty("owner.url")
    public String getOwnerUrl() {
        return ownerUrl;
    }

    @JsonProperty("owner.url")
    public void setOwnerUrl(String ownerUrl) {
        this.ownerUrl = ownerUrl;
    }

    @JsonProperty("owner.avatar_120_url")
    public String getOwnerAvatar120Url() {
        return ownerAvatar120Url;
    }

    @JsonProperty("owner.avatar_120_url")
    public void setOwnerAvatar120Url(String ownerAvatar120Url) {
        this.ownerAvatar120Url = ownerAvatar120Url;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}