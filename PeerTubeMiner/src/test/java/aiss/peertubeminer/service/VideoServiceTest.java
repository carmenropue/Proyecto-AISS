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
        Video v = videoService.VideoFromId(119312);
        assertNotNull(v, "The video is null");
        System.out.println(v);
    }

    @Test
    @DisplayName("Get videos from channel")
    void findVideosFromChannel() {
        List<Video> videos = videoService.VideosFromChannel("transport_evolved_main", 3);
        assertFalse(videos.isEmpty(), "The list of comments is empty");
        System.out.println(videos);
    }
}
