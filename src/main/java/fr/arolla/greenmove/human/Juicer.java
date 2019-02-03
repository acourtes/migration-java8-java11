package fr.arolla.greenmove.human;

import fr.arolla.greenmove.Locomotion;
import fr.arolla.greenmove.LocomotionBatteryState;
import fr.arolla.greenmove.LocomotionProvider;
import fr.arolla.greenmove.LocomotionState;
import fr.arolla.greenmove.Scooter;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class Juicer {

    // A juicer can only hold 10 scooters at the same time
    private static final int MAX_NUMBER_OF_SCOOTERS = 10;
    private List<Locomotion> locomotions;
    private final LocomotionProvider providerWorkFor;
    private Provider provider;

    List<Locomotion> askProviderListOfLocomotionsToJuice() {
        List<Locomotion> collectedScootersToJuice = new ArrayList<>();

        while (collectedScootersToJuice.size() != 10) {
            int currentNumberOfCollectedScooters = collectedScootersToJuice.size();
            final List<Locomotion> listOfLocomotionsToTakeOf = provider.getListOfScootersToTakeOf();
            List<Locomotion> tempCollectedScootersToJuice = getScootersToJuice(listOfLocomotionsToTakeOf,
                    MAX_NUMBER_OF_SCOOTERS - currentNumberOfCollectedScooters);
            collectedScootersToJuice.addAll(tempCollectedScootersToJuice);
        }
        // TODO Use Java 10 new stuffs
        return Collections.unmodifiableList(collectedScootersToJuice);
    }

    private List<Locomotion> getScootersToJuice(List<Locomotion> listOfLocomotionsToTakeOf
            , int numberOfScootersToGet) {
        return listOfLocomotionsToTakeOf.stream()
                .filter(l -> l instanceof Scooter)
                .filter(l -> l.getProvider() == this.getProviderWorkFor())
                .filter(l -> !l.isRented())
                .filter(l -> !l.getBatteryState().equals(LocomotionBatteryState.FULL))
                .filter(l -> l.getState() == LocomotionState.EXCELLENT
                        || l.getState() == LocomotionState.GOOD)
                .limit(numberOfScootersToGet)
                .collect(Collectors.toList());
    }
}
