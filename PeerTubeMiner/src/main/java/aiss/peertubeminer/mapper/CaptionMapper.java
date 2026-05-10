package aiss.peertubeminer.mapper;

import aiss.peertubeminer.model.peertube.Caption;
import aiss.peertubeminer.model.videominer.VMCaption;

public class CaptionMapper {
    public static VMCaption toVMCaption(Caption caption) {

        VMCaption vm = new VMCaption();

        vm.setId("");
        vm.setLink(caption.getFileUrl());
        vm.setLanguage(caption.getLanguage() != null ? caption.getLanguage().getLabel() : null);

        return vm;
    }
}
