package demo.request;


import org.hibernate.validator.constraints.NotEmpty;


public class EmployeeCreateRequest {

    @NotEmpty(message = "Firstname should not be empty")
    private String firstName;
    @NotEmpty(message = "Lastname should not be empty")
    private String lastName;
    @NotEmpty(message = "Email should not be empty")
    private String email;

    public String getEmail() {
        return email;
    }

    public EmployeeCreateRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public EmployeeCreateRequest setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public EmployeeCreateRequest setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

}
