package aiss.peertubeminer.mapper;

import aiss.peertubeminer.model.peertube.Channel;
import aiss.peertubeminer.model.videominer.VMChannel;

public class ChannelMapper {
    public static VMChannel toVMChannel(Channel channel) {

        VMChannel vm = new VMChannel();

        vm.setId(String.valueOf(channel.getId()));
        vm.setName(channel.getName());
        vm.setDescription(channel.getDescription());
        vm.setCreatedTime(channel.getCreatedAt());

        return vm;
    }
}
