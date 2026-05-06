package aiss.peertubeminer.service;

import aiss.peertubeminer.mapper.ChannelMapper;
import aiss.peertubeminer.mapper.VideoMapper;
import aiss.peertubeminer.model.peertube.Channel;
import aiss.peertubeminer.model.peertube.Video;
import aiss.peertubeminer.model.videominer.VMChannel;
import aiss.peertubeminer.model.videominer.VMVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ChannelService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    VideoService videoService;

    String baseURI = "https://peertube.tv/api/v1";
    String videoMinerUri = "http://localhost:8080/videominer/v1/channels";

    public Channel getChannel(String channelHandle) {
        String uri = baseURI + "/video-channels/" + channelHandle;
        return restTemplate.getForObject(uri, Channel.class);
    }

    //GET https://peertube.tv/api/v1/video-channels/{channelHandle}  channelHandle: transport_evolved_main
    public VMChannel getChannelWithVideos(String channelHandle, Integer maxVideos, Integer maxComments) {
        String uri = baseURI + "/video-channels/" + channelHandle;
        try {
            Channel ptChannel = restTemplate.getForObject(uri, Channel.class);

            if (ptChannel != null) {
                VMChannel vmChannel = ChannelMapper.toVMChannel(ptChannel);

                List<Video> videos = videoService.getVideosFromChannel(channelHandle, maxVideos);
                List<Video> videosWithCC = videoService.getVideosWithCC(videos, maxComments);
                List<VMVideo> vmVideos = videosWithCC.stream().filter(v -> v.getPublishedAt() != null && !v.getPublishedAt().isBlank())
                        .map(VideoMapper::toVMVideo)
                        .toList();
                vmChannel.setVideos(vmVideos);
                return vmChannel;
            }
        } catch (HttpClientErrorException e) {
            System.err.println("Client error: " + e.getStatusCode() + "-" + e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            System.err.println("Server error: " + e.getStatusCode() + "-" + e.getResponseBodyAsString());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    public VMChannel getChannelAndSendToMiner (String channelHandle, Integer maxVideos, Integer maxComments) {
        VMChannel vmChannel = getChannelWithVideos(channelHandle, maxVideos, maxComments);

        if (vmChannel != null) {
            sendToVideoMiner(vmChannel);
        }

        return vmChannel;
    }

    private void sendToVideoMiner (VMChannel channel){
        try{
            restTemplate.postForObject(videoMinerUri, channel, VMChannel.class);
            System.out.println("Channel sent to VideoMiner");
        } catch (Exception e){
            System.err.println("Error sending POST to VideoMiner: " + e.getMessage());
        }
    }
}
