package fr.arolla.interfaces.services;

import com.fasterxml.jackson.databind.JsonNode;
import fr.arolla.interfaces.model.Event;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class MusicalAfterWorkReaderTest {

    @Test
    public void should_retrieve_Korean_Karaoke_Event() throws IOException {

        //setup
        MusicalAfterWorkReader musicalAfterWorkReader= new MusicalAfterWorkReader();

        //test
        JsonNode eventsFrom = musicalAfterWorkReader.getEventsFrom("musicalAfterWork.json");
        Event event  = musicalAfterWorkReader.map(eventsFrom);

        //asssert
        assertThat(event.getId()).isEqualTo(1);
        assertThat(event.getName()).contains("Korean Karaoke");
        assertThat(event.getDesciption()).contains("Be drunk after 1 shot and sing K-pop");
        assertThat(event.getProfile()).contains("K-pop addict, or music hater");
        assertThat(event.getMaxPeople()).isEqualTo(12);
        assertThat(event.getMinPeople()).isEqualTo(2);
        assertThat(event.getDurationInHours()).isEqualTo(2);
    }
}