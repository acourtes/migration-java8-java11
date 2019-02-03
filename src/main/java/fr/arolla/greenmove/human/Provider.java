package fr.arolla.greenmove.human;

import fr.arolla.greenmove.Locomotion;
import fr.arolla.greenmove.LocomotionProvider;
import fr.arolla.greenmove.Scooter;
import fr.arolla.greenmove.utils.Randomizer;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class Provider {

    private LocomotionProvider providerName;

    List<Locomotion> getListOfScootersToTakeOf() {
        List<Locomotion> scooters = Randomizer.randomizer.objects(Scooter.class, 30).collect(Collectors.toList());

        scooters.forEach(s -> s.setProvider(this.getProviderName()));

        return scooters;
    }
}
