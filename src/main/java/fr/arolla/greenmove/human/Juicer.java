package fr.arolla.greenmove.human;

import fr.arolla.greenmove.Locomotion;
import fr.arolla.greenmove.LocomotionBatteryState;
import fr.arolla.greenmove.LocomotionProvider;
import fr.arolla.greenmove.LocomotionState;
import fr.arolla.greenmove.Scooter;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
class Juicer {

    // A juicer can only hold 10 scooters at the same time
    private static final int MAX_NUMBER_OF_SCOOTERS = 10;
    private List<Locomotion> locomotions;
    private final LocomotionProvider providerWorkFor;
    private Provider provider;

    List<Locomotion> askProviderListOfLocomotionsToJuice() {
        final List<Locomotion> collectedScootersToJuice = new ArrayList<>();

        while (collectedScootersToJuice.size() != 10) {
            int currentNumberOfCollectedScooters = collectedScootersToJuice.size();
            final List<Locomotion> listOfLocomotionsToTakeOf = provider.getListOfScootersToTakeOf();
            List<Locomotion> tempCollectedScootersToJuice = getScootersToJuice(listOfLocomotionsToTakeOf,
                    MAX_NUMBER_OF_SCOOTERS - currentNumberOfCollectedScooters);
            collectedScootersToJuice.addAll(tempCollectedScootersToJuice);
        }

        // Why copyOf is better than Collectors.toUnmodifiableList() ?
        return List.copyOf(collectedScootersToJuice);
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

    /**
     * The principle is : we get a list of scooters to juice. While the battery is low, we take scooters.
     * At the first scooter with a different level of battery, we stop the process.
     * The returned list is not modifiable.
     * @return The list of scooters
     */
    List<Locomotion> getOptimisticQuicklyScootersToJuice() {
        final List<Locomotion> listOfLocomotionsToTakeOf = provider.getListOfScootersToTakeOf();

        /*for (Locomotion locomotion : listOfLocomotionsToTakeOf) {
            if (locomotion.getBatteryState() != LocomotionBatteryState.LOW) {
                break;
            }

            collectedScootersToJuice.add(locomotion);
        }*/

        return listOfLocomotionsToTakeOf.stream()
                .takeWhile(s -> s.getBatteryState() == LocomotionBatteryState.LOW)
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     * The principle is : we get a list of scooters to juice. While the battery is full, we don't take scooters.
     * At the first scooter with a different level of battery, we take all the remaining.
     * The returned list is not modifiable.
     * @return The list of scooters
     */
    List<Locomotion> getPessimisticQuicklyScootersToJuice() {
        final List<Locomotion> listOfLocomotionsToTakeOf = provider.getListOfScootersToTakeOf();

        /*for (Iterator<Locomotion> iterator = listOfLocomotionsToTakeOf.iterator(); iterator.hasNext();) {
            Locomotion locomotion = iterator.next();
            if (locomotion.getBatteryState() == LocomotionBatteryState.FULL) {
                iterator.remove();
            } else {
                break;
            }
        }*/

        return listOfLocomotionsToTakeOf.stream()
                .dropWhile(s -> s.getBatteryState() == LocomotionBatteryState.FULL)
                .collect(Collectors.toUnmodifiableList());
    }
}
