package aiss.peertubeminer.mapper;

import aiss.peertubeminer.model.peertube.Video;
import aiss.peertubeminer.model.videominer.VMVideo;

public class VideoMapper {
    public static VMVideo toVMVideo(Video video) {

        VMVideo vm = new VMVideo();

        vm.setId(String.valueOf(video.getId()));
        vm.setName(video.getName());
        vm.setDescription(video.getDescription());

        if (video.getPublishedAt() != null && !video.getPublishedAt().isBlank()) {
            vm.setReleaseTime(video.getPublishedAt());
        }

        if (video.getAccount() != null) {
            vm.setAuthor(UserMapper.toVMUser(video.getAccount()));
        }

        if (video.getComments() != null) {
            vm.setComments(
                    video.getComments().stream()
                            .map(CommentMapper::toVMComment)
                            .toList()
            );
        }

        if (video.getCaptions() != null) {
            vm.setCaptions(
                    video.getCaptions().stream()
                            .map(CaptionMapper::toVMCaption)
                            .toList()
            );
        }

        return vm;
    }
}
