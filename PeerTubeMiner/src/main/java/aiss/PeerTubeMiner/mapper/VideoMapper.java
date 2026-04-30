package aiss.PeerTubeMiner.mapper;

import aiss.PeerTubeMiner.model.peerTube.Video;
import aiss.PeerTubeMiner.model.videominer.VMVideo;

public class VideoMapper {
    public static VMVideo toVMVideo(Video video) {

        VMVideo vm = new VMVideo();

        vm.setId(String.valueOf(video.getId()));
        vm.setName(video.getName());
        vm.setDescription(video.getDescription());
        vm.setRealeaseTime(video.getPublishedAt());

        return vm;
    }
}
