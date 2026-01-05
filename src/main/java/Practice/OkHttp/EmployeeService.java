package Practice.OkHttp;

import Practice.OkHttp.Model.CreateNewEmployeeRequest;
import Practice.OkHttp.Model.CreateNewEmployeeResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;

public class EmployeeService {

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    private final String URL = "http://51.250.26.13:8083/employee";
    private final String token;
    private final String companyID;

    public EmployeeService(String token, String companyID) {
        this.token = token;
        this.companyID = companyID;
    }

    public String addEmployee(String firstname, String lastname, String phone) throws IOException {
        CreateNewEmployeeRequest employee = new CreateNewEmployeeRequest(firstname, lastname, Integer.parseInt(companyID), phone);
        String json = mapper.writeValueAsString(employee);

        RequestBody reqBody = RequestBody.create(json, MediaType.get("application/json"));
        Request request = new Request.Builder()
                .url(URL)
                .header("x-client-token", token)
                .post(reqBody)
                .build();

        Response response = client.newCall(request).execute();

        return mapper.readValue(response.body().string(), CreateNewEmployeeResponse.class).id();
    }

}
