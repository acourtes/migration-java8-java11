package fr.arolla.greenmove.human;

import fr.arolla.greenmove.Locomotion;
import lombok.Data;
import lombok.NonNull;

import java.util.Optional;

@Data
public class User {

    public static final String SAD = ":-(";
    private String id;
    private Provider provider;
    private boolean isUsingALocomotion;
    private boolean isHappy;

    Optional<Locomotion> tryAccessToLocomotion(Locomotion locomotion) {
        if (locomotion.isPublicLocomotion() && !locomotion.isRented()) {
            return Optional.of(locomotion);
        }

        return Optional.empty();
    }

    String getAPublicLocomotion(@NonNull Locomotion locomotion) {
        if (tryAccessToLocomotion(locomotion).isPresent()) {
            return rentProcess(locomotion);
        } else {
            return makeUserSad();
        }
    }

    String getAScooter() {
        Optional<Locomotion> scooterToRent = provider.getAScooterToRent();

        if (scooterToRent.isPresent()) {
            return rentProcess(scooterToRent.get());
        } else {
            return makeUserSad();
        }
    }

    private String makeUserSad() {
        this.setHappy(false);
        return SAD;
    }

    private String rentProcess(Locomotion locomotion) {
        if (locomotion.isPublicLocomotion() && !locomotion.isRented()) {
            this.setUsingALocomotion(true);
            this.setHappy(true);
            locomotion.setRented(true);

            return locomotion.getId();
        }

        return "";
    }
}
