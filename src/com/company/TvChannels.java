package com.company;

public abstract class TvChannels {
    String channelName;
    String channelLanguage;
    String category;
    int price;

    public TvChannels(String channelName, String channelLanguage, String category, int price) {
        this.channelName = channelName;
        this.channelLanguage = channelLanguage;
        this.category = category;
        this.price = price;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelLanguage() {
        return channelLanguage;
    }

    public void setChannelLanguage(String channelLanguage) {
        this.channelLanguage = channelLanguage;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
