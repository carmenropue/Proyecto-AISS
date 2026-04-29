package aiss.PeerTubeMiner.service;

import aiss.PeerTubeMiner.model.peerTube.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class commentServiceTest {

    @Autowired
    CommentService commentService;

    @Test
    @DisplayName("Get comments from video")
    void findComments() {
        List<Comment> comments = commentService.commentsFromVideo(119312);
        assertFalse(comments.isEmpty(), "The list of comments is empty");
        System.out.println(comments);
    }
}
