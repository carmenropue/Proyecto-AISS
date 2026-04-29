package aiss.PeerTubeMiner.service;

import aiss.PeerTubeMiner.model.peerTube.Channel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@SpringBootTest
public class channelServiceTest {

    @Autowired
    ChannelService channelService;

    @Test
    @DisplayName("Get a video channel")
    void getChannel() {
        Channel c = channelService.getChannel("transport_evolved_main");
        assertNotNull(c, "The channel is null");
        System.out.println(c);
    }
}
