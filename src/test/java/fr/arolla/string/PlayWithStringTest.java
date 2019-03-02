package fr.arolla.string;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayWithStringTest {

    @Test
    public void isBlank_string_test() {
        final String myBlankString = " ";

        assertThat(myBlankString).isNotEmpty();
        // TODO Use a Java 11 new feature for this trivial test
        assertThat(myBlankString.trim().isEmpty()).isTrue();
    }

    @Test
    public void get_all_lines_from_a_string() {
        final String lineSeparator = System.lineSeparator();

        final String myFantasticString = "Once upon a time" + lineSeparator
                + "There was a princess" + lineSeparator
                + "Who coded all day long in Java";

        // TODO Use a Java 11 new feature to get a stream and no more an array
        // Why is it better than a split ?
        String[] splittedString = myFantasticString.split(lineSeparator);

        assertThat(splittedString.length).isEqualTo(3);
    }

    @Test
    public void strip_leading_a_string() {
        // \u2009 represents a thin space
        String stringToStripLeading = " \u2009  Oops, I think my space bar has a problem ";
        final String expectedStripedLeadingString = "Oops, I think my space bar has a problem ";


        // TODO Use a Java 11 new feature to make this simply
        stringToStripLeading = stringToStripLeading.replaceAll("\\u2009", "");
        while (Character.isWhitespace(stringToStripLeading.charAt(0))) {
            stringToStripLeading = stringToStripLeading.substring(1);
        }

        assertThat(stringToStripLeading).isEqualTo(expectedStripedLeadingString);
    }

    @Test
    public void strip_tailing_a_string() {
        // \u2009 represents a thin space
        String stringToStripTailing = " I think my space bar has still problems   \u2009    ";
        final String expectedStripedTailingString =" I think my space bar has still problems";

        // TODO Use a Java 11 new feature to make this simply
        stringToStripTailing = stringToStripTailing.replaceAll("\\u2009", "");
        while (Character.isWhitespace(stringToStripTailing.charAt(stringToStripTailing.length() - 1))) {
            stringToStripTailing = stringToStripTailing.substring(0, stringToStripTailing.length() - 1);
        }

        assertThat(stringToStripTailing).isEqualTo(expectedStripedTailingString);
    }

    @Test
    public void strip_a_string() {
        // \u2009 represents a thin space
        String stringToStrip = "\u2009  I can't stand anymore this f* space bar  \u2009";
        final String expectedStripedString ="I can't stand anymore this f* space bar";

        // TODO Use a Java 11 new feature to make this in one step
        stringToStrip = stringToStrip.replaceAll("\\u2009", "");

        assertThat(stringToStrip.trim()).isEqualTo(expectedStripedString);
    }

    @Test
    public void multiply_my_string() {
        String laugh = "ah";
        StringBuilder bigLaughBuilder = new StringBuilder();
        // 10 times "ah"
        String bigLaugh = "ahahahahahahahahahah";

        // TODO Use a Java 11 feature to make that in one line
        for (int i = 0; i < 10; i++) {
            bigLaughBuilder.append(laugh);
        }

        assertThat(bigLaughBuilder.toString()).isEqualTo(bigLaugh);
    }
}
