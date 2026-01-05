package Practice.OkHttp;

import Practice.OkHttp.Model.AuthRequest;
import Practice.OkHttp.Model.AuthResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;

public class AuthService {

    private OkHttpClient client = new OkHttpClient();
    private ObjectMapper mapper = new ObjectMapper();

    private final String URL = "http://51.250.26.13:8083/auth/login";
    private String LOGIN;
    private String PASSWORD;

    public AuthService(String login, String password) {
        this.LOGIN = login;
        this.PASSWORD = password;
    }

    public String getToken() throws IOException {
        AuthRequest auth = new AuthRequest(LOGIN, PASSWORD);
        String json = mapper.writeValueAsString(auth);

        RequestBody reqBody = RequestBody.create(json, MediaType.get("application/json"));
        Request request = new Request.Builder()
                .url(URL)
                .post(reqBody)
                .build();

        Response response = client.newCall(request).execute();

        return mapper.readValue(response.body().string(), AuthResponse.class).userToken();
    }

}
