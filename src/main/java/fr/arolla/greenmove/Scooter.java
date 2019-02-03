package fr.arolla.greenmove;

public class Scooter extends Locomotion {

    public Scooter() {
        super.setCategory(LocomotionCategory.SCOOTER);
    }

    @Override
    public String toString() {
        return "Scooter with id " + this.getId();
    }

}
