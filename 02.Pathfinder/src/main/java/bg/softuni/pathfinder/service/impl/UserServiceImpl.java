package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.model.User;
import bg.softuni.pathfinder.model.dto.UserLoginDTO;
import bg.softuni.pathfinder.model.dto.UserProfileDTO;
import bg.softuni.pathfinder.model.dto.UserRegisterDTO;
import bg.softuni.pathfinder.repository.UserRepository;
import bg.softuni.pathfinder.service.CurrentUser;
import bg.softuni.pathfinder.service.UserService;
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

    public void register(UserRegisterDTO userRegisterDTO) {
        this.userRepository.saveAndFlush(this.map(userRegisterDTO));
    }

    public boolean login(UserLoginDTO userLoginDTO) {
        User user = this.userRepository.findByUsername(userLoginDTO.getUsername()).orElse(null);
        if (userLoginDTO.getPassword() != null && user != null && user.getPassword() != null) {
            boolean success = this.passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword());
            if (success) {
                this.currentUser.setLoggedIn(true);
                this.currentUser.setUser(user);
                return true;
            } else {
                this.currentUser.clean();
                return false;
            }
        } else {
            return false;
        }
    }

    public void logout() {
        this.currentUser.clean();
    }

    public UserProfileDTO getUserProfile() {
        return (UserProfileDTO)this.modelMapper.map(this.currentUser.getUser(), UserProfileDTO.class);
    }

    private User map(UserRegisterDTO userRegisterDTO) {
        User user = (User)this.modelMapper.map(userRegisterDTO, User.class);
        user.setPassword(this.passwordEncoder.encode(userRegisterDTO.getPassword()));
        return user;
    }
}