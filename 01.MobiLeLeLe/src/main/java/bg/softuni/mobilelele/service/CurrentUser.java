package bg.softuni.mobilelele.service;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {
    private String fullName;
    private boolean isLoggedIn;

    public CurrentUser() {
    }

    public String getFullName() {
        return this.fullName;
    }

    public CurrentUser setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public boolean isLoggedIn() {
        return this.isLoggedIn;
    }

    public CurrentUser setLoggedIn(boolean loggedIn) {
        this.isLoggedIn = loggedIn;
        return this;
    }

    public void clean() {
        this.fullName = null;
        this.isLoggedIn = false;
    }
}