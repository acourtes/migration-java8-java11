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

public class MusicalAfterWorkReader implements DB {

    private static final String RESOURCES = "src/resources/";
    private static final String MUSICAL_AFTER_WORK_DB_JSON = RESOURCES +"musicalAfterWork.json";
    private static final String KOREAN_KARAOKE_EVENT = "koreanKaraoke";
    private static final String CHRISTOPHE_MAE_CONCERT = "christopheMaeConcert";
    private static final String EVENTS = "events";

    @Override
    public JsonNode getEventsFrom(String fileName) throws IOException {
        JsonNode rooNode = readFile();
        JsonNode eventsNode = getEventsNote(rooNode);
        JsonNode data = eventsNode.get(KOREAN_KARAOKE_EVENT);
        return data;
    }

    @Override
    public Event map(JsonNode eventData) {
        return setData(eventData);
    }

    private Event setData(JsonNode eventData) {
        Event event = new Event();
        event.setDesciption(eventData.get("description").toString());
        event.setId(Integer.valueOf(String.valueOf(eventData.get("id"))));
        event.setName(String.valueOf(eventData.get("name")));
        event.setMaxPeople(Integer.valueOf(String.valueOf(eventData.get("maxPeople"))));
        event.setProfile(String.valueOf(eventData.get("profile")));
        event.setMinPeople(Integer.valueOf(String.valueOf(eventData.get("minPeople"))));
        event.setDurationInHours(Integer.valueOf(String.valueOf(eventData.get("durationInHours"))));
        return event;
    }

    private static JsonNode getEventsNote(JsonNode root) {
        ObjectNode objectNode = (ObjectNode) root;
        return objectNode.get(EVENTS);
    }

    private static JsonNode readFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        URL resource = createFromMusicalActivityDB();
        JsonFactory factory = new JsonFactory();
        JsonParser jsonParser = factory.createParser(resource);
        return mapper.readTree(jsonParser);
    }

    private static URL createFromMusicalActivityDB() throws MalformedURLException {
        File myFile = new File(MUSICAL_AFTER_WORK_DB_JSON);
        try {
            return myFile.toURI().toURL();
        } catch (MalformedURLException e) {
            throw new MalformedURLException(e.getMessage());
        }
    }
}
