package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.dto.UserRegistrationDTO;
import bg.softuni.mobilelele.model.entity.User;
import bg.softuni.mobilelele.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    private UserServiceImpl toTest;

    @Captor
    private ArgumentCaptor<User> userEntityCaptor;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @BeforeEach
    void setUp() {
        toTest = new UserServiceImpl(
                mockUserRepository,
                new ModelMapper(),
                mockPasswordEncoder
        );
    }

    @Test
    void testUserRegistration() {
        // Arrange

        UserRegistrationDTO userRegistrationDTO =
                new UserRegistrationDTO()
                        .setFirstName("Anna")
                        .setLastName("Dimitrova")
                        .setPassword("topsecret")
                        .setEmail("anna@example.com");

        when(mockPasswordEncoder.encode(userRegistrationDTO.getPassword()))
                .thenReturn(userRegistrationDTO.getPassword() + userRegistrationDTO.getPassword());


        // ACT
        toTest.registerUser(userRegistrationDTO);

        // Assert
        verify(mockUserRepository).save(userEntityCaptor.capture());

        User actualSavedEntity = userEntityCaptor.getValue();

        Assertions.assertNotNull(actualSavedEntity);
        Assertions.assertEquals(userRegistrationDTO.getFirstName(), actualSavedEntity.getFirstName());
        Assertions.assertEquals(userRegistrationDTO.getLastName(), actualSavedEntity.getLastName());
        Assertions.assertEquals(userRegistrationDTO.getPassword() + userRegistrationDTO.getPassword(),
                actualSavedEntity.getPassword());
        Assertions.assertEquals(userRegistrationDTO.getEmail(), actualSavedEntity.getEmail());
    }
}
