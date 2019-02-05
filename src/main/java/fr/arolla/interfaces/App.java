package fr.arolla.interfaces;

import com.fasterxml.jackson.databind.JsonNode;
import fr.arolla.interfaces.model.Event;
import fr.arolla.interfaces.services.EscapeGameReader;

import java.io.IOException;

public class App {

    private static EscapeGameReader escapeGameReader= new EscapeGameReader();

    public static void main(String[] args) throws IOException {
        JsonNode events = getEvents("escapeGame");
        Event event = escapeGameReader.map(events);
        System.out.println(event.toString());
    }

    private static JsonNode getEvents(String activity) throws IOException {
        try {
            return escapeGameReader.getEventsFrom("escapeGame");
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }
}
