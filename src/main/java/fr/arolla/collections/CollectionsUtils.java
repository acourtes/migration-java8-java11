package fr.arolla.collections;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static fr.arolla.greenmove.utils.Randomizer.randomizer;

final class CollectionsUtils {

    private CollectionsUtils(){}

    static List<String> generateUnmodifiableListOfStrings() {
        // You can also use this kind of thing
        // List.of("One", "Two", "Three")
        return randomizer.objects(String.class, 10).collect(Collectors.toUnmodifiableList());
    }

    static Set<String> generateUnmodifiableSetOfStrings() {
        // You can also use this kind of thing
        // Set.of("One", "Two", "Three")
        return randomizer.objects(String.class, 10).collect(Collectors.toUnmodifiableSet());
    }

    static Map<String, String> generateUnmodifiableMapOfStrings() {
        // You can also use this kind of thing
        // Map.of("One", "1", "Two", "2", "Three", "3");
        return randomizer.objects(String.class, 10)
                .collect(Collectors.toUnmodifiableMap(Function.identity(), Function.identity()));
    }
}
