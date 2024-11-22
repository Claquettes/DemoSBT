package local.epul4a.model;

import java.util.Date;

public class Person {
    private static Integer id = 0;
    private Integer personId;
    private String firstName;
    private String lastName;
    private String email;
    private Date dob; // Changed to Date

    public Person() {}

    public Person(String firstName, String lastName, String email, Date dob) {
        this.personId = id++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
    }

    public Person(String firstName, String lastName) {
        this.personId = id++;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }
}
