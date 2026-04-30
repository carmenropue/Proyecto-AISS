package aiss.peertubeminer.mapper;

import aiss.peertubeminer.model.peertube.Caption;
import aiss.peertubeminer.model.videominer.VMCaption;

public class CaptionMapper {
    public static VMCaption toVMCaption(Caption caption) {

        VMCaption vm = new VMCaption();

        vm.setId("1");
        vm.setLink(caption.getFileUrl());
        vm.setLanguage(String.valueOf(caption.getLanguage()));

        return vm;
    }
}
