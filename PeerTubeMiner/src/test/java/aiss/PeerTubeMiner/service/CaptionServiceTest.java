package aiss.PeerTubeMiner.service;

import aiss.PeerTubeMiner.model.peerTube.Caption;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class CaptionServiceTest {

    @Autowired
    CaptionService captionService;

    @Test
    @DisplayName("Get captions from video")
    void findCaption() {
        List<Caption> captions = captionService.captionsFromVideo(119312);
        assertFalse(captions.isEmpty(), "The list of captions is empty");
        System.out.println(captions);
    }
}
