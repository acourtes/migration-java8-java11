package fr.arolla.interfaces.model;

import java.util.Objects;

public class Event {
    private int Id;
    private String name;
    private String desciption;
    private String profile;
    private int maxPeople;
    private int minPeople;
    private int durationInHours;

    public void setId(int id) {
        Id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public void setMinPeople(int minPeople) {
        this.minPeople = minPeople;
    }

    public void setDurationInHours(int durationInHours) {
        this.durationInHours = durationInHours;
    }

    @Override
    public String toString() {
        return "Event{" +
                "Id=" + Id +
                ", name=" + name +
                ", desciption=" + desciption +
                ", profile=" + profile +
                ", maxPeople=" + maxPeople +
                ", minPeople=" + minPeople +
                ", durationInHours=" + durationInHours +
                '}';
    }
}
