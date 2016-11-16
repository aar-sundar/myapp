package demo.controller;

import demo.model.Employee;
import demo.request.EmployeeCreateRequest;
import demo.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/emp")
public class EmployeeController {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @ApiOperation(value = "", notes = "Creates an Employee record in DB")
    @RequestMapping(value="/add", method = RequestMethod.POST)
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeCreateRequest request){
        LOG.info("Calling createEmployee");
        return new ResponseEntity<> (employeeService.createEmployee(request), HttpStatus.CREATED);
    }

    @ApiOperation(value = "", notes = "Get an Employee record in DB for a given Id")
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Employee getEmployeeById(@PathVariable String id){
        LOG.info("Getting Employee for the id={}", id);
        return employeeService.getEmployeeById(id);
    }

    @ApiOperation(value = "", notes = "Get an Employee record in DB for a given email")
    @RequestMapping(value="/get", method = RequestMethod.GET)
    public Employee getEmployeeByEmail(@RequestParam String email){
        LOG.info("Getting Employee for the email={}", email);
        return employeeService.getEmployeeByEmail(email);
    }
}
