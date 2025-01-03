package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.User;
import bg.softuni.pathfinder.model.enums.RoleType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {
    private User user;
    private boolean isLoggedIn;

    public CurrentUser() {
    }

    public User getUser() {
        return this.user;
    }

    public CurrentUser setUser(User user) {
        this.user = user;
        return this;
    }

    public boolean isLoggedIn() {
        return this.isLoggedIn;
    }

    public CurrentUser setLoggedIn(boolean loggedIn) {
        this.isLoggedIn = loggedIn;
        return this;
    }

    public boolean isAdmin() {
        return this.user.getRoles().stream().anyMatch((r) -> r.getName().equals(RoleType.ADMIN));
    }

    public void clean() {
        this.user = null;
        this.isLoggedIn = false;
    }
}