package com.company;

public class MovieChannel extends TvChannels{
    int additionalFee = 15;
    public MovieChannel(String channelName,
                        String channelLanguage,
                        String category, int price) {
        super(channelName, channelLanguage, category, price);
    }

    @Override
    public int getPrice() {
        return super.getPrice() + additionalFee;
    }
}
