package fr.arolla.interfaces.services;

import com.fasterxml.jackson.databind.JsonNode;
import fr.arolla.interfaces.model.Event;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class NaturalActivityReaderTest {

    @Test
    public void should_retrieve_paintBall_Event() throws IOException {
        //setup
        NaturalActivityReader naturalActivityReader = new NaturalActivityReader();

        //test
        JsonNode eventsFrom = naturalActivityReader.getEventsFrom("naturalActivity.json");
        Event event = naturalActivityReader.map(eventsFrom);

        //asssert
        assertThat(event.getId()).isEqualTo(2);
        assertThat(event.getName()).contains("Paint Ball Star Wars Role play");
        assertThat(event.getDesciption()).contains("Clone Wars Remake");
        assertThat(event.getProfile()).contains("Be better than StormTroopers");
        assertThat(event.getMaxPeople()).isEqualTo(30);
        assertThat(event.getMinPeople()).isEqualTo(10);
        assertThat(event.getDurationInHours()).isEqualTo(2);
    }
}
