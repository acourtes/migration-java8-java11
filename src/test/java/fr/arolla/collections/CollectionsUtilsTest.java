package fr.arolla.collections;

import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionsUtilsTest {

    @Test(expected = UnsupportedOperationException.class)
    public void must_generate_an_unmodifiable_list_of_strings() {

        List<String> strings = CollectionsUtils.generateUnmodifiableListOfStrings();

        strings.add("Toto");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void must_generate_an_unmodifiable_set_of_strings() {

        Set<String> strings = CollectionsUtils.generateUnmodifiableSetOfStrings();

        strings.add("Toto");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void must_generate_an_unmodifiable_map_of_strings() {

        Map<String, String> strings = CollectionsUtils.generateUnmodifiableMapOfStrings();

        strings.put("Toto", "Titi");
    }
}