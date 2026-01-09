package Practice.OkHttp.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Employee(int id, boolean isActive, String firstName, String lastName, String middleName, String email, String phone, int companyId) {
}
