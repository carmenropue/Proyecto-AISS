package aiss.PeerTubeMiner.service;

import aiss.PeerTubeMiner.model.peerTube.Video;
import aiss.PeerTubeMiner.model.peerTube.VideoSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService {

    @Autowired
    RestTemplate restTemplate;

    String baseURI = "https://peertube.tv/api/v1";

    //GET https://peertube.tv/api/v1/videos/{id}   id= 119312 tiene
    public Video VideoFromId(Integer videoId) {
        Video video = restTemplate.getForObject(baseURI + "/videos/" + videoId, Video.class);
        return video;
    }

    //GET https://peertube.tv/api/v1/video-channels/{channelHandle}/videos

    public List<Video> VideosFromChannel(String channelHandle){
        ResponseEntity<VideoSearch> response = restTemplate
                .getForEntity(baseURI + "/video-channels/" + channelHandle + "/videos", VideoSearch.class);

        if (response.getBody() != null) {
            return response.getBody().getData();
        }
        return new ArrayList<>();
    }
}
