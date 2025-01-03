package bg.softuni.mobilelele.model.dto;

public record UserLoginDTO(String email, String password) {
    public UserLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String email() {
        return this.email;
    }

    public String password() {
        return this.password;
    }
}
