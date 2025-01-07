package bg.softuni.pathfinder.model.dto;

import bg.softuni.pathfinder.model.enums.Level;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterDTO {
    private @NotBlank
    @Size(
            min = 2
    ) String username;
    private @NotBlank
    @Size(
            min = 5
    ) String fullName;
    private @NotBlank
    @Email String email;
    private @Min(0L)
    @Max(90L) Integer age;
    private Level level;
    private @Size(
            min = 5
    ) String password;
    private String confirmPassword;
}