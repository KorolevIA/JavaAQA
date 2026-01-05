package Practice.OkHttp;

import Practice.OkHttp.Model.CreateNewCompanyRequest;
import Practice.OkHttp.Model.CreateNewCompanyResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;

public class CompanyService {

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    private final String URL = "http://51.250.26.13:8083/company";
    private final String token;

    public CompanyService(String token) {
        this.token = token;
    }

    public String addCompany() throws IOException {
        return addCompany("Test", "For test");
    }

    public String addCompany(String name, String description) throws IOException {
        CreateNewCompanyRequest company = new CreateNewCompanyRequest(name, description);
        String json = mapper.writeValueAsString(company);

        RequestBody reqBody = RequestBody.create(json, MediaType.get("application/json"));
        Request request = new Request.Builder()
                .url(URL)
                .header("x-client-token", token)
                .post(reqBody)
                .build();

        Response response = client.newCall(request).execute();

        return mapper.readValue(response.body().string(), CreateNewCompanyResponse.class).id();
    }

}
