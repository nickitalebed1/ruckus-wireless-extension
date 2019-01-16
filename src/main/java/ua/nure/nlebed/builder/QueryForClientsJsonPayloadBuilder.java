package ua.nure.nlebed.builder;

import org.springframework.stereotype.Component;
import ua.nure.nlebed.domain.RuckusZone;

import java.util.List;

@Component
public class QueryForClientsJsonPayloadBuilder {

    private static final String BODY = "{\n" +
            "  \"filters\": [\n" +
            "    {\n" +
            "      \"type\": \"DOMAIN\",\n" +
            "      \"value\": \"8b2081d5-9662-40d9-a3db-2a3cf4dde3f7\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"type\": \"DOMAIN\",\n" +
            "      \"value\": \"8b2081d5-9662-40d9-a3db-2a3cf4dde3f7\"\n" +
            "    }\n" +
            "\n" +
            "  ],\n" +
            "  \"fullTextSearch\": {\n" +
            "    \"type\": \"AND\",\n" +
            "    \"value\": \"\"\n" +
            "  },\n" +
            "  \"attributes\": [\n" +
            "    \"*\"\n" +
            "  ],\n" +
            "  \"page\": 1,\n" +
            "  \"limit\": 50\n" +
            "}";

    public String getBody(List<RuckusZone> ruckusZones) {
        return BODY;
    }

}
