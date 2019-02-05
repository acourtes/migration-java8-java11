package fr.arolla.interfaces.services;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.arolla.interfaces.model.Event;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class EscapeGameReader implements DB {

    private static final String RESOURCES = "src/resources/";
    private static final String ESCAPE_GAME_DB_JSON = RESOURCES + "escapeGame.json";
    private static final String INDEPENDENCE_DAY_EVENT = "independenceDay";
    private static final String GO_TO_MARS = "goToMars";
    private static final String EVENTS = "events";

    @Override
    public JsonNode getEventsFrom(String fileName) throws IOException {
        JsonNode rooNode = readFile();
        JsonNode eventsNode = getEventsNote(rooNode);
        return eventsNode.get(INDEPENDENCE_DAY_EVENT);
    }

    @Override
    public Event map(JsonNode eventData) {
        Event event = new Event();
        event.setId(Integer.valueOf(String.valueOf(eventData.get("id"))));
        event.setName(String.valueOf(eventData.get("name")));
        event.setDesciption(eventData.get("description").toString());
        event.setProfile(String.valueOf(eventData.get("profile")));
        event.setMaxPeople(Integer.valueOf(String.valueOf(eventData.get("maxPeople"))));
        event.setMinPeople(Integer.valueOf(String.valueOf(eventData.get("minPeople"))));
        event.setDurationInHours(Integer.valueOf(String.valueOf(eventData.get("durationInHours"))));
        return event;
    }

    private static JsonNode getEventsNote(JsonNode root) {
        ObjectNode objectNode = (ObjectNode) root;
        return objectNode.get(EVENTS);
    }

    private static URL createFromEscapeGameActivityDB() throws MalformedURLException {
        File myFile = new File(ESCAPE_GAME_DB_JSON);
        try {
            return myFile.toURI().toURL();
        } catch (MalformedURLException e) {
            throw new MalformedURLException(e.getMessage());
        }
    }

    private static JsonNode readFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = new JsonFactory();
        URL resource = createFromEscapeGameActivityDB();
        JsonParser jsonParser = factory.createParser(resource);
        return mapper.readTree(jsonParser);
    }
}
