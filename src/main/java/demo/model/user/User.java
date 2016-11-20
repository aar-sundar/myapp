package demo.model.user;

import org.springframework.data.annotation.Id;


public class User {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private UserDetail userDetail;

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public User setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String toString(){
        return "id="+id+"; firstName="+firstName+"; lastName="+lastName + "; email="+email;
    }
}
