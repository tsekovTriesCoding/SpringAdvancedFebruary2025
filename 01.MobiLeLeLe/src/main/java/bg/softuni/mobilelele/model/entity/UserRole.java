package bg.softuni.mobilelele.model.entity;

import bg.softuni.mobilelele.model.enums.RoleEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class UserRole extends BaseEntity {
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    public UserRole() {
    }

    public RoleEnum getRole() {
        return role;
    }

    public UserRole setRole(RoleEnum role) {
        this.role = role;
        return this;
    }
}
