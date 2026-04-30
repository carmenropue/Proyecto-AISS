package aiss.DailyMotionMiner.service;

import aiss.DailyMotionMiner.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoService {
    @Autowired
    RestTemplate restTemplate;
    String baseUri = "https://api.dailymotion.com";

    //GET https://api.dailymotion.com/video/{videoId}
    public Video getVideoFromId(String id){
        String uri = baseUri+"/video"+"/"+id+"?fields=id%2Cdescription%2Ccreated_time'";
        try{
            return restTemplate.getForObject(uri, Video.class);
        } catch ( HttpClientErrorException e){
            System.err.println("Client error: " +e.getStatusCode() +" - "+e.getResponseBodyAsString());
        } catch ( HttpServerErrorException e){
            System.err.println("Server error: " +e.getStatusCode() +" - "+e.getResponseBodyAsString());
        } catch ( Exception e){
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }
    //GET  https://api.dailymotion.com/user/{userId}/videos

    public List<Video> getVideosFromChannel(String userId){
        String uri = baseUri+"/users/"+userId+"/videos";
        Video[] videos;
        try{
            videos = restTemplate.getForObject(uri, Video[].class);
            return Arrays.stream(videos).toList();
        } catch ( HttpClientErrorException e){
            System.err.println("Client error: " +e.getStatusCode() +" - "+e.getResponseBodyAsString());
        } catch ( HttpServerErrorException e){
            System.err.println("Server error: " +e.getStatusCode() +" - "+e.getResponseBodyAsString());
        } catch ( Exception e){
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }
}

