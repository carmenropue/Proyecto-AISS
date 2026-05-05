package aiss.DailyMotionMiner.mapper;

import aiss.DailyMotionMiner.model.dailymotion.Channel;
import aiss.DailyMotionMiner.model.videominer.VMChannel;

public class ChannelMapper {
    public static VMChannel toVMChannel(Channel dmChannel) {
        if (dmChannel == null) return null;

        VMChannel vmChannel = new VMChannel();

        vmChannel.setId(dmChannel.getId());
        vmChannel.setName(dmChannel.getScreenname());
        vmChannel.setDescription(dmChannel.getDescription());

        if (dmChannel.getCreatedTime() != null) {
            vmChannel.setCreatedTime(String.valueOf(dmChannel.getCreatedTime()));
        }

        return vmChannel;
    }
}
