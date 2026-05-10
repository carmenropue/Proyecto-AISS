package aiss.peertubeminer.service;

import aiss.peertubeminer.model.peertube.Channel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@SpringBootTest
public class ChannelServiceTest {

    @Autowired
    ChannelService channelService;

    @Test
    @DisplayName("Get a video channel")
    void getChannel() {
        Channel c = channelService.getChannel("transport_evolved_main");
        assertNotNull(c, "The channel is null");
        System.out.println(c);
    }

    @Test
    @DisplayName("Get all channels")
    void getChannelWithVideos() {
        Channel c = channelService.getChannel("transport_evolved_main");
        assertNotNull(c, "The channel is null");
        System.out.println(c);
    }
}