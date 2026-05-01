package aiss.peertubeminer.service;

import aiss.peertubeminer.model.peertube.Caption;
import aiss.peertubeminer.model.peertube.Comment;
import aiss.peertubeminer.model.peertube.Video;
import aiss.peertubeminer.model.peertube.VideoSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CaptionService captionService;

    @Autowired
    CommentService commentService;

    String baseURI = "https://peertube.tv/api/v1";

    //GET https://peertube.tv/api/v1/videos/{id}   id= 119312 tiene
    public Video getVideoFromId(Integer videoId) {
        Video video = restTemplate.getForObject(baseURI + "/videos/" + videoId, Video.class);
        return video;
    }

    //GET https://peertube.tv/api/v1/video-channels/{channelHandle}/videos

    public List<Video> getVideosFromChannel(String channelHandle, Integer maxVideos){
        ResponseEntity<VideoSearch> response = restTemplate
                .getForEntity(baseURI + "/video-channels/" + channelHandle + "/videos?count=" + maxVideos, VideoSearch.class);

        if (response.getBody() != null) {
            return response.getBody().getData();
        }
        return new ArrayList<>();
    }

    public List<Video> getVideosWithCC(List<Video> videos,  Integer maxComments){

        for(Video video : videos){
            List<Comment> comments = commentService.getCommentsFromVideo(video.getId(), maxComments);
            List<Caption> captions = captionService.getCaptionsFromVideo(video.getId());
            video.setComments(comments);
            video.setCaptions(captions);
        }
        return videos;
    }
}
