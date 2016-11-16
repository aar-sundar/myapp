package demo.service;

import demo.model.Employee;
import demo.request.EmployeeCreateRequest;


public interface EmployeeService {

    String createEmployee(EmployeeCreateRequest request);

    Employee getEmployeeById(String id);

    Employee getEmployeeByEmail(String email);
}
