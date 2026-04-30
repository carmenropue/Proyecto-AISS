package aiss.peertubeminer.mapper;

import aiss.peertubeminer.model.peertube.Pictures;
import aiss.peertubeminer.model.peertube.User;
import aiss.peertubeminer.model.videominer.VMUser;

public class UserMapper {
    public static VMUser toVMUser(User user) {

        VMUser vm = new VMUser();

        vm.setId(String.valueOf(user.getId()));
        vm.setName(user.getName());
        vm.setUser_link(user.getUrl());

        if (user.getAvatars() != null && !user.getAvatars().isEmpty()) {
            Pictures pic = user.getAvatars().get(0);
            vm.setPicture_link(pic.getPath());
        }

        return vm;
    }
}
