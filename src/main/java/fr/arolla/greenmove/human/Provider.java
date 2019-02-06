package fr.arolla.greenmove.human;

import fr.arolla.greenmove.Locomotion;
import fr.arolla.greenmove.LocomotionBatteryState;
import fr.arolla.greenmove.LocomotionProvider;
import fr.arolla.greenmove.Scooter;
import fr.arolla.greenmove.utils.Randomizer;
import lombok.Data;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class Provider {

    private LocomotionProvider providerName;

    List<LocomotionProvider> getAvailableProviders() {
        // Imagine we call a DAO here and the list is not fixed
        List<LocomotionProvider> availableProviders = Arrays.stream(LocomotionProvider.values())
                                                                        .collect(Collectors.toList());
        // Why is it important to return an unmodifiable list ?
        return Collections.unmodifiableList(availableProviders);
    }

    List<Locomotion> getListOfScootersToTakeOf() {
        List<Locomotion> scooters = Randomizer.randomizer.objects(Scooter.class, 30).collect(Collectors.toList());

        scooters.forEach(s -> s.setProvider(this.getProviderName()));

        return scooters;
    }

    Optional<Locomotion> getAScooterToRent() {
        Locomotion scooter = Randomizer.randomizer.nextObject(Scooter.class).setRented(false);

        if (scooter.getBatteryState() == LocomotionBatteryState.FULL) {
            return Optional.of(scooter);
        }

        return Optional.empty();
    }
}
