package FinishProject.core.dataProvider;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;

public class DataProvider {

    private final ObjectMapper mapper;
    private final JsonNode node;

    public DataProvider() throws IOException {
        this.mapper = new ObjectMapper();
        node = mapper.readTree(Path.of("src/test/resources/data.json").toFile());
    }

    public String getURL() {
        return node.get("url").asText();
    }

    public String getLogin() {
        return node.get("login").asText();
    }

    public String getPassword() {
        return node.get("password").asText();
    }

}
