package fr.arolla.interfaces.services;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.arolla.interfaces.model.Data;

import java.io.IOException;
import java.net.URL;

public class EscapeGameReader implements DB {

    private static final ClassLoader CLASS_LOADER = EscapeGameReader.class.getClassLoader();
    private static final String ESCAPE_GAME_DB_JSON = "escapeGameDB.json";
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
    public Data map(JsonNode eventData) {
        Data data = new Data();
        data.setId(Integer.valueOf(String.valueOf(eventData.get("id"))));
        data.setDesciption(eventData.get("description").toString());
        data.setProfile(String.valueOf(eventData.get("profile")));
        data.setMaxPeople(Integer.valueOf(String.valueOf(eventData.get("maxPeople"))));
        data.setMinPeople(Integer.valueOf(String.valueOf(eventData.get("minPeople"))));
        data.setDurationInHours(Integer.valueOf(String.valueOf(eventData.get("durationInHours"))));
        return data;
    }

    private static JsonNode getEventsNote(JsonNode root) {
        ObjectNode objectNode = (ObjectNode) root;
        return objectNode.get(EVENTS);
    }

    private static JsonNode readFile() throws IOException {
        URL resource = CLASS_LOADER.getResource(ESCAPE_GAME_DB_JSON);
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = new JsonFactory();
        JsonParser jsonParser = factory.createParser(resource);
        return mapper.readTree(jsonParser);
    }
}
