package aiss.peertubeminer.service;

import aiss.peertubeminer.model.peertube.Comment;
import aiss.peertubeminer.model.peertube.CommentSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    RestTemplate restTemplate;

    String baseURI = "https://peertube.tv/api/v1";

    //GET https://peertube.tv/api/v1/videos/{id}/comment-threads
    public List<Comment> getCommentsFromVideo(Integer videoId, Integer maxComments) {

        List<Comment> comments = new ArrayList<>();
        String uri = baseURI + "/videos/" + videoId + "/comment-threads?count=" + maxComments;
        try {
            ResponseEntity<CommentSearch> response =
                    restTemplate.getForEntity(uri, CommentSearch.class);

            if (response.getBody() != null && response.getBody().getData() != null) {
                comments = response.getBody().getData();
            }

        } catch (HttpClientErrorException e) {
            System.err.println("Client error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            System.err.println("Server error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }

        return comments;
    }

    //119312

}
