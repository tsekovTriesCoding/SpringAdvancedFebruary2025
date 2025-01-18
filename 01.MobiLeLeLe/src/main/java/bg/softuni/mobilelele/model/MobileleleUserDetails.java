package bg.softuni.mobilelele.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.UUID;

public class MobileleleUserDetails extends User {

    private final UUID  uuid;
    private final String firstName;
    private final String lastName;

    public MobileleleUserDetails(String username,
                                 String password,
                                 Collection<? extends GrantedAuthority> authorities, UUID uuid,
                                 String firstName,
                                 String lastName) {
        super(username, password, authorities);
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UUID getUuid() {
        return uuid;
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
