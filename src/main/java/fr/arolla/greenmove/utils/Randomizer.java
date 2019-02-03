package fr.arolla.greenmove.utils;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;

public class Randomizer {

    private Randomizer(){}

    public static EnhancedRandom randomizer = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
            .scanClasspathForConcreteTypes(true)
            .build();
}
