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
        // TODO Use Java 10 new stuffs
        final List<String> strings = randomizer.objects(String.class, 10).collect(Collectors.toList());

        return Collections.unmodifiableList(strings);
    }

    static Set<String> generateUnmodifiableSetOfStrings() {
        // TODO Use Java 10 new stuffs
        Set<String> strings = randomizer.objects(String.class, 10).collect(Collectors.toSet());

        return Collections.unmodifiableSet(strings);
    }

    static Map<String, String> generateUnmodifiableMapOfStrings() {
        // TODO Use Java 10 new stuffs
        Map<String, String> stringMap = randomizer.objects(String.class, 10)
                .collect(Collectors.toMap(Function.identity(), Function.identity()));

        return Collections.unmodifiableMap(stringMap);
    }
}
