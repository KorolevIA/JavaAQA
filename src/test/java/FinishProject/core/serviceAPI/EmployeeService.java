package FinishProject.core.serviceAPI;

import FinishProject.core.model.CreateEmployeeRequest;
import FinishProject.core.model.CreateEmployeeResponse;
import FinishProject.core.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;

public class EmployeeService {

    private final OkHttpClient client;
    private final ObjectMapper mapper;

    private final String URL;
    private final String token;
    private final int companyID;

    public int getCompanyID() {
        return companyID;
    }

    public String getToken() {
        return token;
    }

    public EmployeeService(String URL, String token, int companyID) {
        this.client = new OkHttpClient();
        this.mapper = new ObjectMapper();
        this.URL = URL;
        this.token = token;
        this.companyID = companyID;
    }

    public int createEmployee(String firstName, String lastName, String phone) throws IOException {
        CreateEmployeeRequest employee = new CreateEmployeeRequest(firstName, lastName, companyID, phone);
        String json = mapper.writeValueAsString(employee);

        RequestBody body = RequestBody.create(json, MediaType.get("application/json"));
        Request request = new Request.Builder()
                .url(URL)
                .header("x-client-token", token)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return mapper.readValue(response.body().string(), CreateEmployeeResponse.class).id();
    }

    public Employee getEmployeeById(int id) throws IOException {
        Request request = new Request.Builder()
                .url(URL + "/" + id)
                .get()
                .build();

        Response response = client.newCall(request).execute();
        return mapper.readValue(response.body().string(), Employee.class);
    }

}
