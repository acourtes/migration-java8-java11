package fr.arolla.interfaces.services;

import com.fasterxml.jackson.databind.JsonNode;
import fr.arolla.interfaces.model.Event;

import java.io.IOException;

public interface DB {

    JsonNode getEventsFrom(String fileName) throws IOException;

    Event map(JsonNode eventData);
}

