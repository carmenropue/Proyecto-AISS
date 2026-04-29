package aiss.PeerTubeMiner.service;

import aiss.PeerTubeMiner.model.peerTube.Caption;
import aiss.PeerTubeMiner.model.peerTube.CaptionSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CaptionService {

    @Autowired
    RestTemplate restTemplate;

    String baseURI = "https://peertube.tv/api/v1";

    //GET https://peertube.tv/api/v1/videos/{id}/captions   id= 119312 tiene
    public List<Caption> captionsFromVideo(Integer videoId) {
        ResponseEntity<CaptionSearch> response = restTemplate
                .getForEntity(baseURI + "/videos/" + videoId + "/captions", CaptionSearch.class);

        if (response.getBody() != null) {
            return response.getBody().getData();
        }
        //List<Caption> captions = new ArrayList<>();

        return new  ArrayList<>();
    }

}
