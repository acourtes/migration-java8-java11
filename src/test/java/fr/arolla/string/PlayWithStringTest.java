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
        final String myFantasticString = "Once upon a time\nThere was a princess\nWho coded all day long in Java";

        // TODO Use a Java 11 new feature to get a stream and no more a table
        // Why is it better than a split ?
        String[] splittedString = myFantasticString.split("\n");

        assertThat(splittedString.length).isEqualTo(3);
    }

    @Test
    public void strip_leading_a_string() {
        // strip leading
        String stringToStripLeading = "   Oops, I think my space bar has a problem";
        final String expectedStripedLeadingString = "Oops, I think my space bar has a problem";

        // TODO Use a Java 11 new feature to make this simply
        while (Character.isWhitespace(stringToStripLeading.charAt(0))) {
            stringToStripLeading = stringToStripLeading.substring(1);
        }

        assertThat(stringToStripLeading).isEqualTo(expectedStripedLeadingString);
    }

    @Test
    public void strip_tailing_a_string() {
        String stringToStripTailing = "I think my space bar has still problems       ";
        final String expectedStripedTailingString ="I think my space bar has still problems";

        // TODO Use a Java 11 new feature to make this simply
        while (Character.isWhitespace(stringToStripTailing.charAt(stringToStripTailing.length() - 1))) {
            stringToStripTailing = stringToStripTailing.substring(0, stringToStripTailing.length() - 1);
        }

        assertThat(stringToStripTailing).isEqualTo(expectedStripedTailingString);
    }

    @Test
    public void strip_a_string() {
        String stringToStrip = "    I can't stand anymore this f* space bar       ";
        final String expectedStripedString ="I can't stand anymore this f* space bar";

        // TODO Use a Java 11 new feature to make this in one step
        // Lead
        while (Character.isWhitespace(stringToStrip.charAt(0))) {
            stringToStrip = stringToStrip.substring(1);
        }
        // Tail
        while (Character.isWhitespace(stringToStrip.charAt(stringToStrip.length() - 1))) {
            stringToStrip = stringToStrip.substring(0, stringToStrip.length() - 1);
        }

        assertThat(stringToStrip).isEqualTo(expectedStripedString);
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
