package aiss.DailyMotionMiner.service;

import aiss.DailyMotionMiner.mapper.CaptionMapper;
import aiss.DailyMotionMiner.mapper.VideoMapper;
import aiss.DailyMotionMiner.model.dailymotion.Captions;
import aiss.DailyMotionMiner.model.dailymotion.CaptionsItem;
import aiss.DailyMotionMiner.model.dailymotion.Video;
import aiss.DailyMotionMiner.model.dailymotion.VideoList;
import aiss.DailyMotionMiner.model.videominer.VMCaption;
import aiss.DailyMotionMiner.model.videominer.VMVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class VideoService {
    @Autowired
    RestTemplate restTemplate;
    String baseUri = "https://api.dailymotion.com";
    String clientId = "f5595653d07994a56fbe";
    String clientSecret = "7ba945a34824a1348661113da7770678df8d473d";

    //GET https://api.dailymotion.com/video/{videoId}
    public Video getVideoFromId(String id){
        String uri = baseUri+"/video"+"/"+id+"?fields=id,title,description,created_time";
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

    public List<VMVideo> getVideosFromChannel(String userId, int maxVideos, int maxPages){
        List<VMVideo> vmVideos = new ArrayList<>();
        try{
            for (int page = 1; page <= maxPages; page++){
                String uri = baseUri + "/user/" + userId + "/videos?limit=" + maxVideos
                        + "&page=" + page
                        + "&fields=id,title,description,created_time,tags";

                VideoList dmVideos = restTemplate.getForObject(uri, VideoList.class);

                if(dmVideos != null && dmVideos.getList() != null) {
                    for (Video dmVideo : dmVideos.getList()) {
                        VMVideo vmVideo = VideoMapper.toVMVideo(dmVideo);

                        vmVideo.setCaptions(getCaptionsFromVideo(dmVideo.getId()));

                        vmVideos.add(vmVideo);
                    }
                }
            }
        } catch (HttpClientErrorException e){
            System.err.println("Client error: " + e.getStatusCode() +" - "+e.getResponseBodyAsString());
        } catch (HttpServerErrorException e){
            System.err.println("Server error: " + e.getStatusCode() +" - "+e.getResponseBodyAsString());
        } catch (Exception e){
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return vmVideos;
    }

    private String getAccessToken() {
        String uri = baseUri + "/oauth/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = "grant_type=client_credentials"
                + "&client_id=" + clientId
                + "&client_secret=" + clientSecret;

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                uri,
                HttpMethod.POST,
                request,
                Map.class
        );

        return response.getBody().get("access_token").toString();
    }

    public List<VMCaption> getCaptionsFromVideo(String videoId) {
        List<VMCaption> vmCaptions = new ArrayList<>();
        String uri = baseUri + "/video/" + videoId + "/subtitles"
                +"&fields=id,url,language";

        try{
            String token = getAccessToken();

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);

            HttpEntity<String> request = new HttpEntity<>(null, headers);

            ResponseEntity<Captions> response = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    request,
                    Captions.class
            );

            Captions dmCaptions = response.getBody();

            if (dmCaptions != null && dmCaptions.getList() != null) {
                for(CaptionsItem dmCaption : dmCaptions.getList()) {
                    vmCaptions.add(CaptionMapper.toVMCaption(dmCaption));
                }
            }
        } catch (Exception e) {
            System.err.println("Error obteniendo captions para el vídeo" + videoId + ": " + e.getMessage());
        }
        return vmCaptions;
    }


}

