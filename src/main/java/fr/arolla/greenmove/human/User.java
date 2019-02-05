package fr.arolla.greenmove.human;

import fr.arolla.greenmove.Locomotion;
import lombok.Data;
import lombok.NonNull;

import java.util.Optional;

@Data
class User {

    private String id;
    private String rentedLocomotionId;
    private Provider provider;
    private boolean isUsingALocomotion;
    private boolean isHappy;

    Optional<Locomotion> tryAccessToLocomotion(Locomotion locomotion) {
        if (locomotion.isPublicLocomotion() && !locomotion.isRented()) {
            return Optional.of(locomotion);
        }

        return Optional.empty();
    }

    void getAPublicLocomotion(@NonNull Locomotion locomotion) {
        // TODO Use Java 9 new stuffs
        if (tryAccessToLocomotion(locomotion).isPresent()) {
            rentProcess(locomotion);
        } else {
            makeUserSad();
        }
    }

    void getAScooter() {
        Optional<Locomotion> scooterToRent = provider.getAScooterToRent();

        // TODO Use Java 9 new stuffs
        if (scooterToRent.isPresent()) {
            rentProcess(scooterToRent.get());
        } else {
            makeUserSad();
        }
    }

    private void makeUserSad() {
        this.setHappy(false);
    }

    private void rentProcess(Locomotion locomotion) {
        if (locomotion.isPublicLocomotion() && !locomotion.isRented()) {
            this.setUsingALocomotion(true);
            this.setHappy(true);
            this.setRentedLocomotionId(locomotion.getId());
            locomotion.setRented(true);
        }
    }
}
