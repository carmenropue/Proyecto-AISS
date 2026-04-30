package aiss.DailyMotionMiner.service;

import aiss.DailyMotionMiner.model.dailymotion.Video;
import aiss.DailyMotionMiner.model.dailymotion.VideoList;
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
        VideoList result = videoService.getVideosFromChannel("x4zjqxy");
        Assertions.assertNotNull(result);
        printVideos(result);
    }
    void printVideos(VideoList videos){
        System.out.println("Page: " + videos.getPage());
        System.out.println("Limit: " + videos.getLimit());
        System.out.println("Total: " + videos.getTotal());
        System.out.println("Has more: " + videos.getHasMore());
        System.out.println("Explicit: " + videos.getExplicit());
        System.out.println("\n=== Videos ===\n");
        for (Video video : videos.getList()){
            System.out.println(video+"\n");

        }
    }
}
