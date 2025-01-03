package bg.softuni.mobilelele.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserRegistrationDTO {
    private @NotEmpty @Size(
            min = 5,
            max = 20
    ) String firstName;
    private @NotEmpty @Size(
            min = 5,
            max = 20
    ) String lastName;
    private @NotEmpty String password;
    private @NotEmpty @Email String email;

    public UserRegistrationDTO() {
    }

    public String getFirstName() {
        return this.firstName;
    }

    public UserRegistrationDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return this.lastName;
    }

    public UserRegistrationDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return this.password;
    }

    public UserRegistrationDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return this.email;
    }

    public UserRegistrationDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String toString() {
        return "UserRegistrationDTO{firstName='" + this.firstName + "', lastName='" + this.lastName + "', password='" + (this.password == null ? "N/A" : "[PROVIDED]") + "', email='" + this.email + "'}";
    }
}
