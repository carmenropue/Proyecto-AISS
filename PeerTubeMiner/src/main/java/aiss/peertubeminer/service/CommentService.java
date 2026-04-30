package aiss.peertubeminer.service;

import aiss.peertubeminer.model.peertube.Comment;
import aiss.peertubeminer.model.peertube.CommentSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    RestTemplate restTemplate;

    String baseURI = "https://peertube.tv/api/v1";

    //GET https://peertube.tv/api/v1/videos/{id}/comment-threads
    public List<Comment> getCommentsFromVideo(Integer videoId) {
        ResponseEntity<CommentSearch> response = restTemplate
                .getForEntity(baseURI + "/videos/" + videoId + "/comment-threads", CommentSearch.class);

        if (response.getBody() != null) {
            return response.getBody().getData();
        }

        return new ArrayList<>();
    }

    //119312

}
