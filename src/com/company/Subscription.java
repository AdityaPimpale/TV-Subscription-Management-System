package com.company;

import java.io.Serializable;

public class Subscription implements Serializable {

    private int installationFee;
    private int numberOfTV;
    private Subscriber subscriber;
    private SubscriptionCycle cycle;
    private String date;

    private int totalFee;

    public Subscription(int numberOfTV,
                        Subscriber subscriber,
                        SubscriptionCycle cycle,
                        String date) {
        this.numberOfTV = numberOfTV;
        this.subscriber = subscriber;
        this.cycle = cycle;
        this.date = date;

        this.installationFee = numberOfTV * 10;
    }

    public int getNumberOfTV() {
        return numberOfTV;
    }

    public void setNumberOfTV(int numberOfTV) {
        this.numberOfTV = numberOfTV;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public SubscriptionCycle getCycle() {
        return cycle;
    }

    public void setCycle(SubscriptionCycle cycle) {
        this.cycle = cycle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotalFee() {
        totalFee = installationFee + 5;
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "installationFee=" + installationFee +
                ", numberOfTV=" + numberOfTV +
                ", subscriber=" + subscriber +
                ", cycle=" + cycle +
                ", date='" + date + '\'' +
                '}';
    }


}
