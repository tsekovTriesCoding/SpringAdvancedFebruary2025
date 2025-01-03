package bg.softuni.pathfinder.model.dto;

public class UserLoginDTO {
    private String username;
    private String password;

    public UserLoginDTO() {
    }

    public String getUsername() {
        return this.username;
    }

    public UserLoginDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return this.password;
    }

    public UserLoginDTO setPassword(String password) {
        this.password = password;
        return this;
    }
}