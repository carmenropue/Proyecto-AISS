package aiss.peertubeminer.service;

import aiss.peertubeminer.model.peertube.Video;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;


@SpringBootTest
public class VideoServiceTest {

    @Autowired
    VideoService videoService;

    @Test
    @DisplayName("Get a video by id")
    void findVideo() {
        Video v = videoService.getVideoFromId(119312);
        assertNotNull(v, "The video is null");
        System.out.println(v);
    }

    @Test
    @DisplayName("Get videos from channel")
    void findVideosFromChannel() {
        List<Video> videos = videoService.getVideosFromChannel("transport_evolved_main", 3);
        assertFalse(videos.isEmpty(), "The list of videos is empty");
        System.out.println(videos);
    }

    @Test
    @DisplayName("Get videos from channel with captions and comments")
    void findVideosWithCC() {
        List<Video> videos = videoService.getVideosFromChannel("transport_evolved_main", 3);
        List<Video>  videosCC = videoService.getVideosWithCC(videos, 3);
        assertFalse(videosCC.isEmpty(), "The list of videos is empty");
        System.out.println(videosCC);
    }
}