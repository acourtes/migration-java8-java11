package fr.arolla.interfaces.model;

public class Data {
    private int Id;
    private String desciption;
    private String profile;
    private int maxPeople;
    private int minPeople;
    private int durationInHours;

    public void setId(int id) {
        Id = id;
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
}
