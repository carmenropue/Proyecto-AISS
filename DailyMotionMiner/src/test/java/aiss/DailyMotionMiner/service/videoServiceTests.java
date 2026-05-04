package aiss.DailyMotionMiner.service;

import aiss.DailyMotionMiner.model.dailymotion.Video;
import aiss.DailyMotionMiner.model.videominer.VMVideo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class videoServiceTests {
    @Autowired
    VideoService videoService;
    @Test
    @DisplayName("Get video from id")
    void testGetVideo(){
        Video result = videoService.getVideoFromId("xa7ktmo");
        Assertions.assertNotNull(result);

        System.out.println(result);
    }
    @Test
    @DisplayName("Get videos from channel")
    void testGetVideosFromChannel(){
        List<VMVideo> result = videoService.getVideosFromChannel("x4zjqxy", 10, 1);
        Assertions.assertNotNull(result);
        System.out.println(result);
    }
}
