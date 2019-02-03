package fr.arolla;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;

public final class Randomizer {

    private Randomizer(){throw new IllegalArgumentException();}

    public static EnhancedRandom randomizer = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
            .scanClasspathForConcreteTypes(true)
            .build();
}
