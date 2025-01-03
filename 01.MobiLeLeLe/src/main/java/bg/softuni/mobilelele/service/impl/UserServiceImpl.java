package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.dto.UserLoginDTO;
import bg.softuni.mobilelele.model.dto.UserRegistrationDTO;
import bg.softuni.mobilelele.model.entity.User;
import bg.softuni.mobilelele.repository.UserRepository;
import bg.softuni.mobilelele.service.CurrentUser;
import bg.softuni.mobilelele.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

    public void registerUser(UserRegistrationDTO userRegistrationDTO) {
        this.userRepository.saveAndFlush(this.map(userRegistrationDTO));
    }

    public boolean login(UserLoginDTO userLoginDTO) {
        User user = this.userRepository.findByEmail(userLoginDTO.email()).orElse(null);
        if (userLoginDTO.password() != null && user != null && user.getPassword() != null) {
            boolean success = this.passwordEncoder.matches(userLoginDTO.password(), user.getPassword());

            if (success) {
                this.currentUser.setLoggedIn(true);
                this.currentUser.setFullName(user.getFirstName() + " " + user.getLastName());
            } else {
                this.currentUser.clean();
            }

        }
        return false;
    }

    public void logout() {
        this.currentUser.clean();
    }

    private User map(UserRegistrationDTO userRegistrationDTO) {
        User user = this.modelMapper.map(userRegistrationDTO, User.class);
        user.setPassword(this.passwordEncoder.encode(userRegistrationDTO.getPassword()));
        return user;
    }
}