package FinishProject.core.serviceAPI;

import FinishProject.core.model.AuthResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;

public class AuthService {

    private final OkHttpClient client;
    private final ObjectMapper mapper;

    private final String URL;

    public AuthService(String URL) {
        this.client = new OkHttpClient();
        this.mapper = new ObjectMapper();
        this.URL = URL;
    }


    public String getToken(String login, String password) throws IOException {
        String json = "{\n" +
                "  \"username\": \"" + login + "\",\n" +
                "  \"password\": \"" + password + "\"\n" +
                "}";

        RequestBody reqBogy = RequestBody.create(json, MediaType.get("application/json"));
        Request request = new Request.Builder()
                .url(URL)
                .post(reqBogy)
                .build();

        Response response = client.newCall(request).execute();
        return mapper.readValue(response.body().string(), AuthResponse.class).userToken();
    }

}
