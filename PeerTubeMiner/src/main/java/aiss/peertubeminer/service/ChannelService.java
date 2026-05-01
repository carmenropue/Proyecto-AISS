package aiss.peertubeminer.service;

import aiss.peertubeminer.model.peertube.Channel;
import aiss.peertubeminer.model.peertube.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ChannelService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    VideoService videoService;

    String baseURI = "https://peertube.tv/api/v1";

    //GET https://peertube.tv/api/v1/video-channels/{channelHandle}  channelHandle: transport_evolved_main
    public Channel getChannel(String channelHandle){
        Channel channel = restTemplate
                .getForObject(baseURI + "/video-channels/" + channelHandle, Channel.class);
        return channel;
    }

    public Channel getChannelWithVideos(String channelHandle, Integer maxVideos, Integer maxComments){
        Channel channel = getChannel(channelHandle);
        List<Video> videos = videoService.getVideosFromChannel(channelHandle, maxVideos);
        List<Video> videosWithCC = videoService.getVideosWithCC(videos, maxComments);

        channel.setVideos(videosWithCC);
        return channel;
    }

}
