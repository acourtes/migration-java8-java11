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

public class NaturalActivityReader implements DB {

    private static final String PAINT_BALL_EVENT = "paintBall";
    private static final String HIKING_EVENT = "hiking";
    private static final String RESOURCES = "src/resources/";
    private static final String EVENTS = "events";
    private static final String NATURAL_ACTIVITY_DB_JSON = RESOURCES + "naturalActivity.json";

    @Override
    public JsonNode getEventsFrom(String fileName) throws IOException {
        JsonNode rooNode = readFile();
        JsonNode eventsNode = getEventsNote(rooNode);
        JsonNode data = eventsNode.get(PAINT_BALL_EVENT);
        return data;
    }

    @Override
    public Event map(JsonNode eventData) {
        Event event = new Event();
        event.setId(Integer.valueOf(String.valueOf(eventData.get("id"))));
        event.setDesciption(eventData.get("description").toString());
        event.setName(String.valueOf(eventData.get("name")));
        event.setMaxPeople(Integer.valueOf(String.valueOf(eventData.get("maxPeople"))));
        event.setDurationInHours(Integer.valueOf(String.valueOf(eventData.get("durationInHours"))));
        event.setMinPeople(Integer.valueOf(String.valueOf(eventData.get("minPeople"))));
        event.setProfile(String.valueOf(eventData.get("profile")));
        return event;
    }

    private static JsonNode readFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = new JsonFactory();
        URL resource = createFromNaturalActivityDB();
        JsonParser jsonParser = factory.createParser(resource);
        return mapper.readTree(jsonParser);
    }

    private static URL createFromNaturalActivityDB() throws MalformedURLException {
        File myFile = new File(NATURAL_ACTIVITY_DB_JSON);
        try {
            return myFile.toURI().toURL();
        } catch (MalformedURLException e) {
            throw new MalformedURLException(e.getMessage());
        }
    }

    private static JsonNode getEventsNote(JsonNode root) {
        ObjectNode objectNode = (ObjectNode) root;
        return objectNode.get(EVENTS);
    }
}
