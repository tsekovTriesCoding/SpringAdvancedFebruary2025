package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.entity.User;
import bg.softuni.mobilelele.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RegisterControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testRegister() throws Exception {
        this.mockMvc.perform(post("/users/register")
                        .param("email", "test@abv.bg")
                        .param("firstName", "Anton")
                        .param("lastName", "Ivanov")
                        .param("password", "12345")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        Optional<User> userOpt = userRepository.findByEmail("test@abv.bg");

        Assertions.assertTrue(userOpt.isPresent());

        User user = userOpt.get();
        Assertions.assertEquals("Anton", user.getFirstName());
        Assertions.assertEquals("Ivanov", user.getLastName());
        Assertions.assertTrue(this.passwordEncoder.matches("12345", user.getPassword()));
    }
}
