package com.company;

public class DocumentaryChannel extends TvChannels{
    int additionalFee = 12;
    public DocumentaryChannel(String channelName,
                              String channelLanguage,
                              String category, int price) {
        super(channelName, channelLanguage, category, price);
    }

    @Override
    public int getPrice() {
        return super.getPrice() + additionalFee;
    }
}
