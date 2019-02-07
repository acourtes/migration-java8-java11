package fr.arolla.interfaces.services;

import com.fasterxml.jackson.databind.JsonNode;
import fr.arolla.interfaces.model.Event;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

public class EscapeGameReaderTest {

    @Test
    public void should_retrieve_Independence_Day_Event() throws IOException {
        //setup
        EscapeGameReader escapeGameReader = new EscapeGameReader();

        //test
        JsonNode eventsFrom = escapeGameReader.getEventsFrom("escapeGameReader.json");
        Event event  = escapeGameReader.map(eventsFrom);

        //asssert
        assertThat(event.getId()).isEqualTo(1);
        assertThat(event.getName()).contains("Independence Day");
        assertThat(event.getDesciption()).contains("Help Will Smith To save the world");
        assertThat(event.getProfile()).contains("Stereotype of an egocentric American Hero");
        assertThat(event.getMaxPeople()).isEqualTo(10);
        assertThat(event.getMinPeople()).isEqualTo(5);
        assertThat(event.getDurationInHours()).isEqualTo(2);
    }
}