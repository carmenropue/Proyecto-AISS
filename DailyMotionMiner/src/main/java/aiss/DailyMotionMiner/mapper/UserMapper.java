package aiss.DailyMotionMiner.mapper;

import aiss.DailyMotionMiner.model.dailymotion.User;
import aiss.DailyMotionMiner.model.videominer.VMUser;

public class UserMapper {
    public static VMUser toVMUser(User user) {
        if (user == null) {
            return null;
        }
        return new VMUser(
                user.getId(),
                user.getUsername(),
                user.getUrl(),
                user.getAvatar25Url()
        );
    }
}

