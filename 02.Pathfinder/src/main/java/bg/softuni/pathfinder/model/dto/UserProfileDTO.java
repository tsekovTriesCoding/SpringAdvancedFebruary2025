package bg.softuni.pathfinder.model.dto;

import bg.softuni.pathfinder.model.enums.Level;

public class UserProfileDTO {
    private String username;
    private String fullName;
    private int age;
    private Level level;

    public UserProfileDTO() {
    }

    public String getUsername() {
        return this.username;
    }

    public UserProfileDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return this.fullName;
    }

    public UserProfileDTO setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public int getAge() {
        return this.age;
    }

    public UserProfileDTO setAge(int age) {
        this.age = age;
        return this;
    }

    public Level getLevel() {
        return this.level;
    }

    public UserProfileDTO setLevel(Level level) {
        this.level = level;
        return this;
    }
}