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
        String employeeID_2 = employee.addEmployee("Петров", "Дмитрий", "89856007070");
        System.out.println("EmployeeID_2 " + employeeID_2);

        List<GetAllEmployeeResponse> employeeList = employee.getAllEmployee();
        System.out.println(employeeList);

        int updateStatusCode = employee.updateLastName(employeeID_1, "Test");
        System.out.println(updateStatusCode);
        int deactivationStatusCode = employee.deactivationEmployee(employeeID_2);
        System.out.println(deactivationStatusCode);

        String employee_1 = employee.getEmployeeByID(employeeID_1);
        System.out.println(employee_1);
        String employee_2 = employee.getEmployeeByID(employeeID_2);
        System.out.println(employee_2);

        int deleteStatusCode = company.deleteCompany(companyID);
        System.out.println(deleteStatusCode);
    }

}
