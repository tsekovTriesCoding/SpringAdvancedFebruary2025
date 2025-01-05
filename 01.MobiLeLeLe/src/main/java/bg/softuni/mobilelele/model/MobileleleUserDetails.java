package bg.softuni.mobilelele.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class MobileleleUserDetails extends User {

    private final String firstName;
    private final String lastName;

    public MobileleleUserDetails(String username,
                                 String password,
                                 Collection<? extends GrantedAuthority> authorities,
                                 String firstName,
                                 String lastName) {
        super(username, password, authorities);

        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        StringBuilder sb = new StringBuilder();

        if (this.firstName != null) {
            sb.append(this.firstName);
        }
        if (this.lastName != null) {
            if (!this.lastName.isEmpty()) {
                sb.append(" ").append(this.lastName);
            }
        }

        return sb.toString();
    }
}
