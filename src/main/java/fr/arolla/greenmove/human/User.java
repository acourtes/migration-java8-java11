package fr.arolla.greenmove.human;

import fr.arolla.greenmove.Locomotion;
import lombok.Data;
import lombok.NonNull;

import java.util.Optional;

@Data
public class User {

    private String id;
    private boolean isUsingALocomotion;

    Optional<Locomotion> tryAccessToLocomotion(Locomotion locomotion) {
        if (locomotion.isPublicLocomotion() && !locomotion.isRented()) {
            return Optional.of(locomotion);
        }

        return Optional.empty();
    }

    String getAScooter(@NonNull Locomotion locomotion) {
        if (locomotion.isAScooter() && tryAccessToLocomotion(locomotion).isPresent()) {
            this.setUsingALocomotion(true);
            locomotion.setRented(true);

            return locomotion.getId();
        }

        return "";
    }
}
