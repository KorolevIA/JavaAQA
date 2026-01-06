package Practice.OkHttp.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GetAllEmployeeResponse(int id, String isActive, String firstName, String lastName, String phone, int companyId) {
}
