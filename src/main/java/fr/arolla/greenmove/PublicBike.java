package fr.arolla.greenmove;

public class PublicBike extends Locomotion {

    public PublicBike() {
        super.setCategory(LocomotionCategory.PUBLIC_BIKE);
    }

    @Override
    public String toString() {
        return "Public bike with id " + this.getId();
    }
}
