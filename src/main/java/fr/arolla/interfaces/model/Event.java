package fr.arolla.interfaces.model;

public class Event {
    private int Id;
    private String name;
    private String desciption;
    private String profile;
    private int maxPeople;
    private int minPeople;
    private int durationInHours;


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public int getMinPeople() {
        return minPeople;
    }

    public void setMinPeople(int minPeople) {
        this.minPeople = minPeople;
    }

    public int getDurationInHours() {
        return durationInHours;
    }

    public void setDurationInHours(int durationInHours) {
        this.durationInHours = durationInHours;
    }

    @Override
    public String toString() {
        return "Event{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", desciption='" + desciption + '\'' +
                ", profile='" + profile + '\'' +
                ", maxPeople=" + maxPeople +
                ", minPeople=" + minPeople +
                ", durationInHours=" + durationInHours +
                '}';
    }
}
