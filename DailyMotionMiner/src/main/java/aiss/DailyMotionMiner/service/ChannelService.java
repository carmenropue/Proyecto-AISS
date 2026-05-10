package aiss.DailyMotionMiner.service;

import aiss.DailyMotionMiner.mapper.ChannelMapper;
import aiss.DailyMotionMiner.model.dailymotion.Channel;
import aiss.DailyMotionMiner.model.videominer.VMChannel;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
public class ChannelService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    VideoService videoService;
    String baseUri = "https://api.dailymotion.com";
    String videoMinerUri = "http://localhost:8080/videominer/v1/channels";

    // GET https://api.dailymotion.com/user/{userId}
    public VMChannel getChannel(String userId, int maxVideos, int maxPages){
        String uri = baseUri+"/user/"+userId+"?fields=id,screenname,description,created_time";
        try{
            Channel dmChannel = restTemplate.getForObject(uri, Channel.class);

            if(dmChannel != null){
                VMChannel vmChannel = ChannelMapper.toVMChannel(dmChannel);
                vmChannel.setVideos(videoService.getVideosFromChannel(userId, maxVideos, maxPages));
                return vmChannel;
            }
        } catch (HttpClientErrorException e){
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND,
                    "Videos not found: " + e.getMessage()
            );
        } catch (HttpServerErrorException e){
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.BAD_GATEWAY,
                    "Dailymotion server error: " + e.getMessage()
            );
        } catch (org.springframework.web.server.ResponseStatusException e) {
            throw e;
        } catch (Exception e){
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR,
                    "Unexpected error: " + e.getMessage()
            );
        }
    }

    public VMChannel getChannelAndSendToMiner(String userId, int maxVideos, int maxPages){
        VMChannel vmChannel = getChannel(userId, maxVideos, maxPages);
        if(vmChannel != null) {
            sendToVideoMiner(vmChannel);
            }
        return vmChannel;
    }

    private void sendToVideoMiner(VMChannel channel) {
        try{
            restTemplate.postForObject(videoMinerUri, channel, VMChannel.class);
            System.out.println("Canal enviado a VideoMiner");
        } catch (Exception e) {
            System.err.println("Error al enviar el POST a VideoMiner: " + e.getMessage());
        }
    }

}
