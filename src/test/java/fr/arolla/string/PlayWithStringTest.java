package fr.arolla.string;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayWithStringTest {

    @Test
    public void get_a_table_with_all_verses() throws FileNotFoundException {
        List<String> poem = readPoem();

        // TODO Use new String class feature in Java 11
        long numberOfVerse = poem.stream()
                .filter(s -> !s.isEmpty())
                .count();
        String[] tabVerses = new String[Long.valueOf(numberOfVerse).intValue()];

        List<String> versesList = poem.stream()
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

        for (int i = 0; i < versesList.size(); i++) {
            tabVerses[i] = versesList.get(i);
        }

        assertThat(tabVerses[Long.valueOf(numberOfVerse).intValue() - 1]).contains("Baudelaire");
    }

    private List<String> readPoem() throws FileNotFoundException {
        URL fileURL = PlayWithStringTest.class.getResource("/baudelaire.txt");
        File baudelaire = new File(fileURL.getFile());
        Scanner scanner = new Scanner(baudelaire).useDelimiter("\n");
        List<String> poem = new ArrayList<>();

        while (scanner.hasNext()) {
            poem.add(scanner.next());
        }
        return poem;
    }
}
