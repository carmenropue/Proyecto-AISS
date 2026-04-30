package aiss.DailyMotionMiner.service;

import aiss.DailyMotionMiner.DailyMotionApplication;
import aiss.DailyMotionMiner.model.Video;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class videoServiceTests {
    @Autowired
    VideoService videoService;
    @Test
    @DisplayName("Get video from id")
    void testGetVideo(){
        Video result = videoService.getVideoFromId("xa7ktmo");
        Assertions.assertNotNull(result);
        printVideo(result);
    }
    void printVideo(Video video){
        System.out.println(video.getId());
        System.out.println(video.getTitle());
        System.out.println(video.getDescription());
        System.out.println(video.getCreatedTime());
    }
}
