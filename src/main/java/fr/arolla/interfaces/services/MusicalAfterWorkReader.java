package fr.arolla.interfaces.services;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.arolla.interfaces.model.Data;

import java.io.IOException;
import java.net.URL;

public class MusicalAfterWorkReader implements DB {

    private static final ClassLoader CLASS_LOADER = MusicalAfterWorkReader.class.getClassLoader();
    private static final String MUSICAL_AFTER_WORK_DB_JSON = "musicalAfterWorkDB.json";
    private static final String KOREAN_KARAOKE_EVENT = "koreanKaraoke";
    private static final String CHRISTOPHE_MAE_CONCERT = "christopheMaeConcert";
    private static final String EVENTS = "events";

    @Override
    public JsonNode getEventsFrom(String fileName) throws IOException {
        JsonNode rooNode = readFileMoreNicely();
        JsonNode eventsNode = getEventsNote(rooNode);
        JsonNode data = eventsNode.get(KOREAN_KARAOKE_EVENT);
        return data;
    }

    @Override
    public Data map(JsonNode eventData) {
        return setData(eventData);
    }

    private Data setData(JsonNode eventData) {
        Data data = new Data();
        data.setDesciption(eventData.get("description").toString());
        data.setId(Integer.valueOf(String.valueOf(eventData.get("id"))));
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

    private static JsonNode readFileMoreNicely() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        URL resource = CLASS_LOADER.getResource(MUSICAL_AFTER_WORK_DB_JSON);
        JsonFactory factory = new JsonFactory();
        JsonParser jsonParser = factory.createParser(resource);
        return mapper.readTree(jsonParser);
    }
}
