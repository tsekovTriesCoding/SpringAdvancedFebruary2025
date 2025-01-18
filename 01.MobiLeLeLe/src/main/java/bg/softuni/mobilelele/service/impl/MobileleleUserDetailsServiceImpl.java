package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.MobileleleUserDetails;
import bg.softuni.mobilelele.model.entity.User;
import bg.softuni.mobilelele.model.entity.UserRole;
import bg.softuni.mobilelele.model.enums.RoleEnum;
import bg.softuni.mobilelele.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MobileleleUserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public MobileleleUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(email)
                .map(MobileleleUserDetailsServiceImpl::map)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found"));
    }

    private static UserDetails map(User user) {
        return new MobileleleUserDetails(
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream().map(UserRole::getRole).map(MobileleleUserDetailsServiceImpl::map).toList(),
                user.getUuid(),
                user.getFirstName(),
                user.getLastName()
        );
    }

    private static GrantedAuthority map(RoleEnum role) {
        return new SimpleGrantedAuthority("ROLE_" + role);
    }
}
