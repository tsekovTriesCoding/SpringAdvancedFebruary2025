package bg.softuni.pathfinder.model.dto;

import bg.softuni.pathfinder.model.enums.Level;
import bg.softuni.pathfinder.validation.annotation.UniqueEmail;
import bg.softuni.pathfinder.validation.annotation.UniqueUsername;
import bg.softuni.pathfinder.validation.annotation.ValidatePasswords;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@ValidatePasswords
public class UserRegisterDTO {
    @NotBlank
    @Size(min = 2)
    @UniqueUsername
    private String username;

    @NotBlank
    @Size(min = 5)
    private String fullName;

    @NotBlank
    @Email
    @UniqueEmail
    private String email;

    @Min(0L)
    @Max(90L)
    private Integer age;

    private Level level;

    @Size(min = 5)
    private String password;

    @Size(min = 5, max = 20, message = "Password length must be between 5 and 20 characters.")
    private String confirmPassword;
}