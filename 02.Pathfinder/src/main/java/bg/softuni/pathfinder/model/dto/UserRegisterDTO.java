package bg.softuni.pathfinder.model.dto;

import bg.softuni.pathfinder.model.enums.Level;
import jakarta.validation.constraints.*;

public class UserRegisterDTO {
    private @NotBlank @Size(
            min = 2
    ) String username;
    private @NotBlank @Size(
            min = 5
    ) String fullName;
    private @NotBlank @Email String email;
    private @Min(0L) @Max(90L) Integer age;
    private Level level;
    private @Size(
            min = 5
    ) String password;
    private String confirmPassword;

    public UserRegisterDTO() {
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Level getLevel() {
        return this.level;
    }

    public UserRegisterDTO setLevel(Level level) {
        this.level = level;
        return this;
    }
}