package aiss.DailyMotionMiner.service;

import aiss.DailyMotionMiner.model.dailymotion.Video;
import aiss.DailyMotionMiner.model.videominer.VMUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import aiss.DailyMotionMiner.model.dailymotion.User;
@Service
public class UserService {
    @Autowired
    RestTemplate restTemplate;
    String baseUri = "https://api.dailymotion.com";

    public VMUser getUserFromVideo(String ownerId) {
        String uri = baseUri + "/user/" + ownerId
                +"?fields=id,username,url,avatar_25_url";
        try{
            User dmUser = restTemplate.getForObject(uri, User.class);
            VMUser vmUser = UserMapper.toVMUser(dmUser);
        } catch ( HttpClientErrorException e){
            System.err.println("Client error: " +e.getStatusCode() +" - "+e.getResponseBodyAsString());
        } catch ( HttpServerErrorException e){
            System.err.println("Server error: " +e.getStatusCode() +" - "+e.getResponseBodyAsString());
        } catch ( Exception e){
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }
}
