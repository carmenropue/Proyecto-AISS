package aiss.PeerTubeMiner.service;

import aiss.PeerTubeMiner.model.peerTube.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChannelService {

    @Autowired
    RestTemplate restTemplate;

    String baseURI = "https://peertube.tv/api/v1";


    //GET https://peertube.tv/api/v1/video-channels/{channelHandle}  channelHandle: transport_evolved_main
    public Channel getChannel(String channelHandle){
        Channel channel = restTemplate
                .getForObject(baseURI + "/video-channels/" + channelHandle, Channel.class);
        return channel;
    }
}
