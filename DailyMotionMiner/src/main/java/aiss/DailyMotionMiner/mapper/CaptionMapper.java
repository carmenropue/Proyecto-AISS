package aiss.DailyMotionMiner.mapper;

import aiss.DailyMotionMiner.model.dailymotion.CaptionsItem;
import aiss.DailyMotionMiner.model.videominer.VMCaption;

public class CaptionMapper {
    public static VMCaption toVMCaption(CaptionsItem dmCaption) {
        VMCaption vmCaption = new VMCaption();

        vmCaption.setLanguage(dmCaption.getLanguage());
        vmCaption.setLink(dmCaption.getUrl());

        return vmCaption;
    }
}
