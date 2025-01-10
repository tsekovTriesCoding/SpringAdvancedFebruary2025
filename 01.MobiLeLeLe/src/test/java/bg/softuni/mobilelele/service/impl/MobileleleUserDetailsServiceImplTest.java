package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.MobileleleUserDetails;
import bg.softuni.mobilelele.model.entity.User;
import bg.softuni.mobilelele.model.entity.UserRole;
import bg.softuni.mobilelele.model.enums.RoleEnum;
import bg.softuni.mobilelele.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MobileleleUserDetailsServiceImplTest {
    private static final String TEST_EMAIL = "user@example.com";
    private static final String NOT_EXISTENT_EMAIL = "noone@example.com";

    private MobileleleUserDetailsServiceImpl toTest;
    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        toTest = new MobileleleUserDetailsServiceImpl(mockUserRepository);
    }

    @Test
    void testLoadUserByUsername_UserFound() {

        // Arrange
        User testUser = new User()
                .setEmail(TEST_EMAIL)
                .setPassword("topsecret")
                .setFirstName("Pesho")
                .setLastName("Petrov")
                .setRoles(List.of(
                        new UserRole().setRole(RoleEnum.ADMIN),
                        new UserRole().setRole(RoleEnum.USER)
                ));

        when(mockUserRepository.findByEmail(TEST_EMAIL))
                .thenReturn(Optional.of(testUser));

        // Act
        UserDetails userDetails = toTest.loadUserByUsername(TEST_EMAIL);

        // Assert
        Assertions.assertInstanceOf(MobileleleUserDetails.class, userDetails);

        MobileleleUserDetails mobileleUserDetails = (MobileleleUserDetails) userDetails;

        Assertions.assertEquals(TEST_EMAIL, userDetails.getUsername());
        Assertions.assertEquals(testUser.getPassword(), userDetails.getPassword());
        Assertions.assertEquals(testUser.getFirstName(), mobileleUserDetails.getFirstName());
        Assertions.assertEquals(testUser.getLastName(), mobileleUserDetails.getLastName());
        Assertions.assertEquals(testUser.getFirstName() + " " + testUser.getLastName(),
                mobileleUserDetails.getFullName());

        var expectedRoles = testUser.getRoles().stream().map(UserRole::getRole).map(r -> "ROLE_" + r).toList();
        var actualRoles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        Assertions.assertEquals(expectedRoles, actualRoles);
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername(NOT_EXISTENT_EMAIL)
        );
    }
}
