package fr.arolla.file;

import fr.arolla.string.PlayWithStringTest;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayWithFileTest {

    private URL fileURL = PlayWithStringTest.class.getResource("/baudelaire.txt");
    private File baudelaire = new File(fileURL.getFile());

    @Test
    public void read_a_file_with_a_scanner() throws FileNotFoundException {
        final Scanner scanner = new Scanner(baudelaire).useDelimiter("\n");

        List<String> poem = new ArrayList<>();

        // TODO Use a Java 9 feature to have something more fluent
        while (scanner.hasNext()) {
            poem.add(scanner.next());
        }

        assertThat(poem).isNotEmpty();
    }

    @Test
    public void read_content_of_a_file_and_get_it_within_a_single_String() throws IOException {
        // TODO Use Java 11 new feature to get the content within a single String
        List<String> strings = Files.readAllLines(baudelaire.toPath());
        Optional<String> myOptionalString = strings.stream().reduce(String::concat);
        String myPoem = null;

        if (myOptionalString.isPresent()) {
            myPoem = myOptionalString.get();
        }

        assertThat(myPoem).isNotNull();
        assertThat(myPoem).isNotBlank();
    }

    @Test
    public void write_string_in_a_file() throws IOException {
        URL fileURL = PlayWithStringTest.class.getResource("/");
        File testFile = new File(fileURL.getFile() + "test.txt");
        List<String> strings = Arrays.asList("Toto", "Tata");

        // TODO Use Java 11 feature to write directly a String without using an Iterable
        Path path = Files.write(testFile.toPath(), strings, StandardOpenOption.CREATE, StandardOpenOption.WRITE);

        assertThat(path).isNotNull();
        assertThat(path).exists();
        assertThat(path).hasFileName("test.txt");
        assertThat(path).hasContent("Toto" + System.lineSeparator() + "Tata");
    }
}
