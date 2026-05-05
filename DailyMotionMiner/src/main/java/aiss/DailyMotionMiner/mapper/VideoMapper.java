package aiss.DailyMotionMiner.mapper;

import aiss.DailyMotionMiner.model.dailymotion.Video;
import aiss.DailyMotionMiner.model.videominer.VMComment;
import aiss.DailyMotionMiner.model.videominer.VMVideo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VideoMapper {
    public static VMVideo toVMVideo(Video dmVideo) {
        VMVideo vmVideo = new VMVideo();

        vmVideo.setId(dmVideo.getId());
        vmVideo.setName(dmVideo.getTitle());
        vmVideo.setDescription(dmVideo.getDescription());

        if (dmVideo.getCreatedTime() != null) {
            vmVideo.setReleaseTime(String.valueOf(dmVideo.getCreatedTime()));
        }

        List<VMComment> vmComments = new ArrayList<>();

        if (dmVideo.getTags() != null) {
            for (String tag : dmVideo.getTags()) {
                VMComment comment = new VMComment();
                comment.setId(UUID.randomUUID().toString());
                comment.setText(tag);
                comment.setCreatedOn(vmVideo.getReleaseTime());
                vmComments.add(comment);
            }
        }

        vmVideo.setComments(vmComments);

        return vmVideo;
    }
}
