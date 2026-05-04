package aiss.DailyMotionMiner.service;

import aiss.DailyMotionMiner.model.dailymotion.Captions;
import aiss.DailyMotionMiner.model.dailymotion.CaptionsItem;
import aiss.DailyMotionMiner.model.dailymotion.Video;
import aiss.DailyMotionMiner.model.dailymotion.VideoList;
import aiss.DailyMotionMiner.model.videominer.VMCaption;
import aiss.DailyMotionMiner.model.videominer.VMComment;
import aiss.DailyMotionMiner.model.videominer.VMVideo;
//import aiss.videominer.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class VideoService {
    @Autowired
    RestTemplate restTemplate;
    String baseUri = "https://api.dailymotion.com";

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
                String uri = baseUri+"/user/"+userId+"/videos?limit="+ maxVideos +  "&page=" + page + "&fields=id,title,description,created_time,tags";

                VideoList dmVideos = restTemplate.getForObject(uri, VideoList.class);

                if(dmVideos != null && dmVideos.getList() != null) {
                    for (Video dmVideo : dmVideos.getList()) {
                        VMVideo vmVideo = new VMVideo();
                        vmVideo.setId(dmVideo.getId());
                        vmVideo.setName(dmVideo.getTitle());
                        vmVideo.setDescription(dmVideo.getDescription());

                        if(dmVideo.getCreatedTime() != null) {
                            vmVideo.setReleaseTime(String.valueOf(dmVideo.getCreatedTime()));
                        }

                        List<VMComment> vmComments = new ArrayList<>();
                        if(dmVideo.getTags() != null){
                            for(String tag: dmVideo.getTags()) {
                                VMComment comment = new VMComment();
                                comment.setId(UUID.randomUUID().toString());
                                comment.setText(tag);
                                comment.setCreatedOn(vmVideo.getReleaseTime());
                                vmComments.add(comment);
                            }
                        }
                        vmVideo.setComments(vmComments);

                        vmVideo.setCaptions(getCaptionsFromVideo(dmVideo.getId()));

                        vmVideos.add(vmVideo);
                    }
                }
            }
        } catch ( HttpClientErrorException e){
            System.err.println("Client error: " +e.getStatusCode() +" - "+e.getResponseBodyAsString());
        } catch ( HttpServerErrorException e){
            System.err.println("Server error: " +e.getStatusCode() +" - "+e.getResponseBodyAsString());
        } catch ( Exception e){
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return vmVideos;
    }

    private List<VMCaption> getCaptionsFromVideo(String videoId) {
        List<VMCaption> vmCaptions = new ArrayList<>();
        String uri = baseUri + "/video/" + videoId + "/subtitles";

        try{
            Captions dmCaptions = restTemplate.getForObject(uri, Captions.class);
            if (dmCaptions != null && dmCaptions.getList() != null) {
                for(CaptionsItem dmCaption : dmCaptions.getList()) {
                    VMCaption vmCaption = new VMCaption();
                    vmCaption.setId(dmCaption.getId());
                    vmCaption.setLanguage(dmCaption.getLanguage());
                    vmCaption.setLink(dmCaption.getUrl());
                    vmCaptions.add(vmCaption);
                }
            }
        } catch (Exception e) {
            System.err.println("Error obteniendo captions para el vídeo" + videoId + ": " + e.getMessage());
        }
        return vmCaptions;
    }


}

