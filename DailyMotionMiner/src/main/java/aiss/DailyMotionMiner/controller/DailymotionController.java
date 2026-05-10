package aiss.DailyMotionMiner.controller;

import aiss.DailyMotionMiner.model.videominer.VMChannel;
import aiss.DailyMotionMiner.model.videominer.VMVideo;
import aiss.DailyMotionMiner.service.ChannelService;
import aiss.DailyMotionMiner.service.VideoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Tag(name= "Dailymotion", description = "Dailymotion API operations")
@RestController
@RequestMapping("/dailymotion/v1")
public class DailymotionController {

    @Autowired
    ChannelService channelService;

    @Autowired
    VideoService videoService;

    //Operacion GET de prueba
    @Operation(summary = "Get channel information",
            description = "Retrieve channel information and videos from Dailymotion",
            tags = {"Dailymotion", "getChannel"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = VMChannel.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })

    @GetMapping("/{id}")
    public VMChannel getChannel (@PathVariable String id, @RequestParam(defaultValue = "10") int maxVideos, @RequestParam(defaultValue = "2") int maxPages){
        return channelService.getChannel(id, maxVideos, maxPages);
    }

    @Operation(summary = "Get video information",
            description = "Retrieve video information and captions from Dailymotion",
            tags = {"Dailymotion", "getVideo"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = VMVideo.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @GetMapping("/video/{id}")
    public VMVideo getVideo(@PathVariable String id) {
        VMVideo video = videoService.getVMVideoFromId(id);
        if (video == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Video not found");
        }
        return video;
    }

    //Operacion POST
    @Operation(summary = "Send channel information to miner",
            description = "Retrieve channel information and videos from Dailymotion and send it to the miner",
            tags = {"Dailymotion", "postChannel"})
    @ApiResponses({
            @ApiResponse(responseCode = "202", content = {@Content(schema = @Schema(implementation = VMChannel.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @PostMapping("/{id}")
    public VMChannel SendChannel(
            @PathVariable String id,
            @RequestParam(defaultValue = "10") int maxVideos,
            @RequestParam(defaultValue = "2") int maxPages) {
        return channelService.getChannelAndSendToMiner(id, maxVideos, maxPages);
    }


}
