package aiss.peertubeminer.controller;

import aiss.peertubeminer.model.peertube.Channel;
import aiss.peertubeminer.model.videominer.VMChannel;
import aiss.peertubeminer.service.ChannelService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.MethodInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("peertubeminer/api/v1")
public class PeerTubeController {

    @Autowired
    ChannelService channelService;

    @GetMapping("/{channelHandle}")
    public VMChannel getChannel(@PathVariable String channelHandle,
                                @RequestParam(name = "maxVideos", defaultValue = "10") @Min(1) @Max(100) Integer maxVideos,
                                @RequestParam(name = "maxComments", defaultValue = "2") @Min(1) @Max(100) Integer maxComments) {
        return channelService.getChannelAndSendToMiner(channelHandle, maxVideos, maxComments); //Se diferencian en los servicios, cada uno consume su API (PeerTube o DailyMotion) pero ambos devuelven el mismo modelo para VideoMiner.
    }
    //Operacion POST
    //TODO Añadir maxPages a la uri de peertube
    @PostMapping("/{id}")
    public VMChannel SendChannel(
            @PathVariable String id,
            @RequestParam(name = "maxVideos", defaultValue = "10") @Min(1) @Max(100) Integer maxVideos,
            @RequestParam(name = "maxComments", defaultValue = "2") @Min(1) @Max(100) Integer maxComments){
        return channelService.getChannelAndSendToMiner(id,maxVideos, maxComments);
    }
}
