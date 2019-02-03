package fr.arolla.greenmove.human;

import fr.arolla.greenmove.Locomotion;
import fr.arolla.greenmove.Scooter;
import fr.arolla.greenmove.utils.Randomizer;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProviderTest {

    @Test
    public void provider_provides_scooters_only_from_him() {
        Provider provider = Randomizer.randomizer.nextObject(Provider.class);

        List<Locomotion> scooters = provider.getListOfScootersToTakeOf();

        assertThat(scooters).isNotNull();
        assertThat(scooters).isNotEmpty();
        scooters.forEach(s -> {
            assertThat(s).isInstanceOf(Scooter.class);
            assertThat(s.getProvider()).isEqualTo(provider.getProviderName());
        });
    }
}