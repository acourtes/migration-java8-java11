package fr.arolla.greenmove;

public class OneWheel extends Locomotion {

    public OneWheel() {
        super.setCategory(LocomotionCategory.ONEWHEEL);
    }

    @Override
    public String toString() {
        return "OneWheel with id " + this.getId();
    }
}
