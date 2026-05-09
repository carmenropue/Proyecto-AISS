package aiss.peertubeminer.service;

import aiss.peertubeminer.mapper.VideoMapper;
import aiss.peertubeminer.model.peertube.Caption;
import aiss.peertubeminer.model.peertube.Comment;
import aiss.peertubeminer.model.peertube.Video;
import aiss.peertubeminer.model.peertube.VideoSearch;
import aiss.peertubeminer.model.videominer.VMVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
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
        String uri = baseURI + "/videos/" + videoId;

        try{
            return restTemplate.getForObject(uri, Video.class);
        } catch (HttpClientErrorException e){
            System.err.println("Client error: " + e.getStatusCode() + "-" + e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            System.err.println("Server error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());

        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    public VMVideo getVMVideoFromId(Integer videoId, Integer maxComments) {
        Video video = getVideoFromId(videoId);
        if (video == null) {
            return null;
        }

        video.setComments(commentService.getCommentsFromVideo(videoId, maxComments));
        video.setCaptions(captionService.getCaptionsFromVideo(videoId));

        return VideoMapper.toVMVideo(video);
    }

    //GET https://peertube.tv/api/v1/video-channels/{channelHandle}/videos

    public List<Video> getVideosFromChannel(String channelHandle, Integer maxVideos) {
        List<Video> videos = new ArrayList<>();

        String uri = baseURI + "/video-channels/" + channelHandle + "/videos?count=" + maxVideos;

        try {
            ResponseEntity<VideoSearch> response =
                    restTemplate.getForEntity(uri, VideoSearch.class);

            if (response.getBody() != null && response.getBody().getData() != null) {
                videos = response.getBody().getData();
            }

        } catch (HttpClientErrorException e) {
            System.err.println("Client error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());

        } catch (HttpServerErrorException e) {
            System.err.println("Server error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());

        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }

        return videos;
    }

    public List<Video> getVideosWithCC(List<Video> videos, Integer maxComments) {

        for (Video video : videos) {
            List<Comment> comments = commentService.getCommentsFromVideo(video.getId(), maxComments);
            List<Caption> captions = captionService.getCaptionsFromVideo(video.getId());

            video.setComments(comments);
            video.setCaptions(captions);
        }

        return videos;
    }
}
