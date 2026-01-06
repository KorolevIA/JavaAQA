package Practice.OkHttp;

import Practice.OkHttp.Model.GetAllEmployeeResponse;

import java.io.IOException;
import java.util.List;

public class Employee {

    static void main() throws IOException {

        String LOGIN = "leonardo";
        String PASSWORD = "leads";

        AuthService auth = new AuthService(LOGIN, PASSWORD);
        String token = auth.getToken();

        CompanyService company = new CompanyService(token);
        String companyID = company.addCompany();
        System.out.println("CompanyID " + companyID);

        EmployeeService employee = new EmployeeService(token, companyID);

        String employeeID_1 = employee.addEmployee("Иванов", "Иван", "89994563345");
        System.out.println("EmployeeID_1 " + employeeID_1);
        int updateStatusCode = employee.updateEmployee(employeeID_1, "lastName", "Test");
        System.out.println(updateStatusCode);

        String employeeID_2 = employee.addEmployee("Петров", "Дмитрий", "89856007070");
        System.out.println("EmployeeID_2 " + employeeID_2);
        int deactivationStatusCode = employee.deactivationEmployee(employeeID_2);
        System.out.println(deactivationStatusCode);

        List<GetAllEmployeeResponse> employeeList = employee.getAllEmployee();
        System.out.println(employeeList);
    }

}
