package aiss.peertubeminer.controller;

import aiss.peertubeminer.model.videominer.VMChannel;
import aiss.peertubeminer.model.videominer.VMVideo;
import aiss.peertubeminer.service.ChannelService;
import aiss.peertubeminer.service.VideoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Tag(name = "PeerTube", description = "PeerTube API operations")
@RestController
@RequestMapping("peertubeminer/api/v1")
public class PeerTubeController {

    @Autowired
    ChannelService channelService;

    @Autowired
    VideoService videoService;

    @Operation(summary = "Get channel information",
            description = "Retrieve channel information and videos from PeerTube",
            tags = {"PeerTube", "getChannel"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = VMChannel.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @GetMapping("/{channelHandle}")
    public VMChannel getChannel(@PathVariable String channelHandle,
                                @RequestParam(name = "maxVideos", defaultValue = "10") @Min(1) @Max(100) Integer maxVideos,
                                @RequestParam(name = "maxComments", defaultValue = "2") @Min(1) @Max(100) Integer maxComments) {
        return channelService.getChannelAndSendToMiner(channelHandle, maxVideos, maxComments); //Se diferencian en los servicios, cada uno consume su API (PeerTube o DailyMotion) pero ambos devuelven el mismo modelo para VideoMiner.
    }

    @Operation(summary = "Get video information",
            description = "Retrieve video information, comments and captions from PeerTube",
            tags = {"PeerTube", "getVideo"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = VMVideo.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @GetMapping("/video/{id}")
    public VMVideo getVideo(@PathVariable Integer id,
                            @RequestParam(name = "maxComments", defaultValue = "2") @Min(1) @Max(100) Integer maxComments) {
        VMVideo video = videoService.getVMVideoFromId(id, maxComments);
        if (video == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Video not found");
        }
        return video;
    }

    //Operacion POST
    //TODO Añadir maxPages a la uri de peertube
    @Operation(summary = "Send channel information to miner",
            description = "Retrieve channel information and videos from PeerTube and send it to the miner",
            tags = {"PeerTube", "postChannel"})
    @ApiResponses({
            @ApiResponse(responseCode = "202", content = {@Content(schema = @Schema(implementation = VMChannel.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @PostMapping("/{id}")
    public VMChannel SendChannel(
            @PathVariable String id,
            @RequestParam(name = "maxVideos", defaultValue = "10") @Min(1) @Max(100) Integer maxVideos,
            @RequestParam(name = "maxComments", defaultValue = "2") @Min(1) @Max(100) Integer maxComments){
        return channelService.getChannelAndSendToMiner(id,maxVideos, maxComments);
    }
}
