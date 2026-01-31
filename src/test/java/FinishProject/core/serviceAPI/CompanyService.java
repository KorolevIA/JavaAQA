package FinishProject.core.serviceAPI;

import FinishProject.core.model.CreateCompanyResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;

public class CompanyService {

    private final OkHttpClient client;
    private final ObjectMapper mapper;

    private final String URL;
    private final String token;

    public CompanyService(String URL, String token) {
        this.client = new OkHttpClient();
        this.mapper = new ObjectMapper();
        this.URL = URL;
        this.token = token;
    }

    public int createCompany(String name, String description) throws IOException {
        String json = "{\n" +
                "  \"name\": \"" + name + "\",\n" +
                "  \"description\": \"" + description + "\"\n" +
                "}";
        RequestBody body = RequestBody.create(json, MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(URL)
                .header("x-client-token", token)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return mapper.readValue(response.body().string(), CreateCompanyResponse.class).id();
    }

}
