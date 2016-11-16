package demo.service;

import demo.exception.AlreadyFoundException;
import demo.exception.InvalidRequestException;
import demo.exception.NotFoundException;
import demo.model.Employee;
import demo.repository.EmployeeRepository;
import demo.request.EmployeeCreateRequest;
import demo.util.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    public String createEmployee(EmployeeCreateRequest request) {
        String email = request.getEmail();
        Employee empFound = findByEmail(email);
        if(empFound != null){
            throw new AlreadyFoundException("Employee with the email already available");
        }
        Employee emp = new Employee();
        emp.setFirstName(request.getFirstName());
        emp.setLastName(request.getLastName());
        emp.setEmail(email);
        Employee empCreated = employeeRepository.save(emp);
        LOG.info("Employee created successfully = {}", empCreated);
        return empCreated.getId();
    }

    @Override
    public Employee getEmployeeById(String id) {
        Employee emp = employeeRepository.findOne(id);
        if(emp == null){
            LOG.error("Employee Information not found for the Id ={}", id);
            throw new NotFoundException("Employee Information not found for the Id:"+ id);
        }
        LOG.info("Employee found in db = {}", emp);
        return  emp;
    }

    @Override
    public Employee getEmployeeByEmail(String email){
        Employee emp = findByEmail(email);
        if(emp == null){
            LOG.error("Employee Information not found for the email ={}", email);
            throw new NotFoundException("Employee Information not found for the email : "+ email);
        } else {
            return emp;
        }
    }

    private Employee findByEmail(String email){
        Employee emp = null;
        if(validateEmail(email)){
            emp = employeeRepository.findByEmail(email);
        }
        return emp;
    }

    private boolean validateEmail(String email){
        if(!Utility.validate(email)){
            LOG.error("Invalid email = {}", email);
            throw new InvalidRequestException("invalid email");
        }
        return true;
    }
}
