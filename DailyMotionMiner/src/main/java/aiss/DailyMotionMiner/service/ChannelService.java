package aiss.DailyMotionMiner.service;

import aiss.DailyMotionMiner.model.dailymotion.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
public class ChannelService {
    @Autowired
    RestTemplate restTemplate;
    String baseUri = "https://api.dailymotion.com";
    // GET https://api.dailymotion.com/user/{userId}
    public Channel getChannel(String userId){
        String uri = baseUri+"/user/"+userId+"?fields=id,screenname,description,created_time";
        try{
            return restTemplate.getForObject(uri, Channel.class);
        } catch ( HttpClientErrorException e){
            System.err.println("Client error: " +e.getStatusCode() +" - "+e.getResponseBodyAsString());
        } catch ( HttpServerErrorException e){
            System.err.println("Server error: " +e.getStatusCode() +" - "+e.getResponseBodyAsString());
        } catch( ResourceAccessException e){
            System.err.println("Resourve access error: " +e.getMessage());
        } catch ( Exception e){
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
}
}
