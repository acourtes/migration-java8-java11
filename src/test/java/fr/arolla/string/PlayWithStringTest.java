package fr.arolla.string;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayWithStringTest {

    @Test
    public void isBlank_string_test() {
        final var myBlankString = " ";

        assertThat(myBlankString).isNotEmpty();
        assertThat(myBlankString.isBlank()).isTrue();
    }

    @Test
    public void get_all_lines_from_a_string() {
        final var lineSeparator = System.lineSeparator();

        final var myFantasticString = "Once upon a time" + lineSeparator
                + "There was a princess" + lineSeparator
                + "Who coded all day long in Java";

        // TODO Use a Java 11 new feature to get a stream and no more a table
        // Why is it better than a split ?
        //String[] splittedString = myFantasticString.split("\n");

        assertThat(myFantasticString.lines().count()).isEqualTo(3);
    }

    @Test
    public void strip_leading_a_string() {
        // \u2009 represents a thin space
        var stringToStripLeading = " \u2009  Oops, I think my space bar has a problem ";
        final var expectedStripedLeadingString = "Oops, I think my space bar has a problem ";

        /*stringToStripLeading = stringToStripLeading.replaceAll("\\u2009", "");

        while (Character.isWhitespace(stringToStripLeading.charAt(0))) {
            stringToStripLeading = stringToStripLeading.substring(1);
        }*/

        assertThat(stringToStripLeading.stripLeading()).isEqualTo(expectedStripedLeadingString);
    }

    @Test
    public void strip_tailing_a_string() {
        // \u2009 represents a thin space
        var stringToStripTailing = " I think my space bar has still problems   \u2009   ";
        final var expectedStripedTailingString =" I think my space bar has still problems";

        /* stringToStripTailing = stringToStripTailing.replaceAll("\\u2009", "");
        while (Character.isWhitespace(stringToStripTailing.charAt(stringToStripTailing.length() - 1))) {
            stringToStripTailing = stringToStripTailing.substring(0, stringToStripTailing.length() - 1);
        }*/

        assertThat(stringToStripTailing.stripTrailing()).isEqualTo(expectedStripedTailingString);
    }

    @Test
    public void strip_a_string() {
        // \u2009 represents a thin space
        var stringToStrip = "\u2009  I can't stand anymore this f* space bar  \u2009";
        final var expectedStripedString ="I can't stand anymore this f* space bar";

        // stringToStrip = stringToStrip.replaceAll("\\u2009", "");

        assertThat(stringToStrip.strip()).isEqualTo(expectedStripedString);
    }

    @Test
    public void multiply_my_string() {
        final var laugh = "ah";
        //StringBuilder bigLaughBuilder = new StringBuilder();
        // 10 times "ah"
        final var bigLaugh = "ahahahahahahahahahah";

        /*for (int i = 0; i < 10; i++) {
            bigLaughBuilder.append(laugh);
        }*/

        final var repeatLaugh = laugh.repeat(10);

        assertThat(repeatLaugh).isEqualTo(bigLaugh);
    }
}