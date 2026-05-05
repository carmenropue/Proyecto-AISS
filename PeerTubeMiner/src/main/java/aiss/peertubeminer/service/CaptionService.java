package aiss.peertubeminer.service;

import aiss.peertubeminer.model.peertube.Caption;
import aiss.peertubeminer.model.peertube.CaptionSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CaptionService {

    @Autowired
    RestTemplate restTemplate;

    String baseURI = "https://peertube.tv/api/v1";

    //GET https://peertube.tv/api/v1/videos/{id}/captions   id= 119312 tiene
    public List<Caption> getCaptionsFromVideo(Integer videoId) {
        List<Caption> captions = new ArrayList<>();
        String uri = baseURI + "/videos/" + videoId + "/captions";
        try {
            ResponseEntity<CaptionSearch> response = restTemplate.getForEntity(uri, CaptionSearch.class);

            if (response.getBody() != null && response.getBody().getData() != null) {
                captions = response.getBody().getData();
            }
        } catch (HttpClientErrorException e) {
            System.err.println("Client error: " + e.getStatusCode() + "-" + e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            System.err.println("Server error: " + e.getStatusCode() + "-" + e.getResponseBodyAsString());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return captions;
    }
}
