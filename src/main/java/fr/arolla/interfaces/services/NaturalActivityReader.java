package fr.arolla.interfaces.services;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.arolla.interfaces.model.Data;

import java.io.IOException;
import java.net.URL;

public class NaturalActivityReader implements DB {

    private static final String PAINT_BALL_EVENT = "paintBall";
    private static final ClassLoader CLASS_LOADER = NaturalActivityReader.class.getClassLoader();
    private static final String HIKING_EVENT = "hiking";
    private static final String EVENTS = "events";
    private static final String NATURAL_ACTIVITY_DB_JSON = "naturalActivity.json";

    @Override
    public JsonNode getEventsFrom(String fileName) throws IOException {
        JsonNode rooNode = readFileMoreNicely();
        JsonNode eventsNode = getEventsNote(rooNode);
        JsonNode data = eventsNode.get(HIKING_EVENT);
        return data;
    }

    @Override
    public Data map(JsonNode eventData) {
        Data data = new Data();
        data.setDesciption(eventData.get("description").toString());
        data.setId(Integer.valueOf(String.valueOf(eventData.get("id"))));
        data.setMaxPeople(Integer.valueOf(String.valueOf(eventData.get("maxPeople"))));
        data.setProfile(String.valueOf(eventData.get("profile")));
        data.setDurationInHours(Integer.valueOf(String.valueOf(eventData.get("durationInHours"))));
        data.setMinPeople(Integer.valueOf(String.valueOf(eventData.get("minPeople"))));
        return data;
    }

    private static JsonNode readFileMoreNicely() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = new JsonFactory();
        URL resource = CLASS_LOADER.getResource(NATURAL_ACTIVITY_DB_JSON);
        JsonParser jsonParser = factory.createParser(resource);
        return mapper.readTree(jsonParser);
    }

    private static JsonNode getEventsNote(JsonNode root) {
        ObjectNode objectNode = (ObjectNode) root;
        return objectNode.get(EVENTS);
    }
}
