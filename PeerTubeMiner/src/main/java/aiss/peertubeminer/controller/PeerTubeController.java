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
@RequestMapping("peertubeminer/api/v1") //cómo diferencio de dailymotion?
public class PeerTubeController {

    @Autowired
    ChannelService channelService;

    @GetMapping("/{channelHandle}")
    public VMChannel getChannel(@PathVariable String channelHandle,
                                @RequestParam(name = "maxVideos", defaultValue = "10") @Min(1) @Max(100) Integer maxVideos) {
        return channelService.getChannelAndSendToMiner(channelHandle, maxVideos); //Se diferencian en los servicios, cada uno consume su API (PeerTube o DailyMotion) pero ambos devuelven el mismo modelo para VideoMiner.
    }
}
