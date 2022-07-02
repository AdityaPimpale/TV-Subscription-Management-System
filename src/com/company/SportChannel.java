package com.company;

public class SportChannel extends TvChannels{
    int additionalFee = 10;
    public SportChannel(String channelName,
                        String channelLanguage,
                        String category, int price) {
        super(channelName, channelLanguage, category, price);
    }

    @Override
    public int getPrice() {
        return super.getPrice() + additionalFee;
    }
}
