package aiss.peertubeminer.controller;

import aiss.peertubeminer.model.videominer.VMChannel;
import aiss.peertubeminer.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/peertube")
public class PeertubePublicController {

    @Autowired
    ChannelService channelService;

        //esto habria que ponerlo dentro del otro controller
    @GetMapping(value = "/tv", produces = MediaType.APPLICATION_JSON_VALUE)
    public VMChannel getChannelPublic(
            @RequestParam(name = "channelHandle", required = false, defaultValue = "transport_evolved_main") String channelHandle,
            @RequestParam(name = "maxVideos", required = false, defaultValue = "10") Integer maxVideos,
            @RequestParam(name = "maxComments", required = false, defaultValue = "2") Integer maxComments
    ) {
        VMChannel vmChannel = channelService.getChannelWithVideos(channelHandle, maxVideos, maxComments);
        if (vmChannel == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Channel not found");
        }
        return vmChannel;
    }

}

