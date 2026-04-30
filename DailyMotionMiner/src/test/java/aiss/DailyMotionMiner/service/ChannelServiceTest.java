package aiss.DailyMotionMiner.service;

import aiss.DailyMotionMiner.model.dailymotion.Channel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ChannelServiceTest {
    @Autowired
    ChannelService channelService;
    @Test
    @DisplayName("Get channel by Id")
    void getChannelById() {
        Channel result = channelService.getChannel("x4zjqxy");
        Assertions.assertNotNull(result);
        System.out.println(result);
    }
}