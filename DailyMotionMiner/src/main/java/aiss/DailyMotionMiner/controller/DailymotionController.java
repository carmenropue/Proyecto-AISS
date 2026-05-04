package aiss.DailyMotionMiner.controller;

import aiss.DailyMotionMiner.model.videominer.VMChannel;
import aiss.DailyMotionMiner.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dailymotion")
public class DailymotionController {

    @Autowired
    ChannelService channelService;

    //Operacion GET de prueba
    @GetMapping("/{id}")
    public VMChannel getChannel (@PathVariable String id, @RequestParam(defaultValue = "10") int maxVideos, @RequestParam(defaultValue = "2") int maxPages){
        return channelService.getChannel(id, maxVideos, maxPages);
    }

    //Operacion POST
    @PostMapping("/{id}")
    public VMChannel SendChannel(
            @PathVariable String id,
            @RequestParam(defaultValue = "10") int maxVideos,
            @RequestParam(defaultValue = "2") int maxPages) {
        return channelService.getChannelAndSendToMiner(id, maxVideos, maxPages);
    }


}
