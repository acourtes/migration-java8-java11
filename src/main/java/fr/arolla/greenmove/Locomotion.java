package fr.arolla.greenmove;

import lombok.Data;

@Data
public abstract class Locomotion {

    private String id;
    private LocomotionProvider provider;
    private LocomotionCategory category;
    private LocomotionBatteryState batteryState;
    private LocomotionState state;
    private boolean isRented;
    private boolean isElectric;

    public boolean isPublicLocomotion() {
        return this.category == LocomotionCategory.SCOOTER
                || this.category == LocomotionCategory.PUBLIC_BIKE;
    }

    public boolean isAScooter() {
        return this.category == LocomotionCategory.SCOOTER;
    }

}
