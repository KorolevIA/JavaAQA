package Practice.OkHttp;

import java.io.IOException;

public class Employee {

    static void main() throws IOException {

        String LOGIN = "leonardo";
        String PASSWORD = "leads";

        AuthService auth = new AuthService(LOGIN, PASSWORD);
        String token = auth.getToken();

        CompanyService company = new CompanyService(token);
        String companyID = company.addCompany();
    }

}
