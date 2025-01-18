package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.MobileleleUserDetails;
import bg.softuni.mobilelele.model.dto.UserRegistrationDTO;

import java.util.Optional;

public interface UserService {
    void registerUser(UserRegistrationDTO userRegistrationDTO);

    Optional<MobileleleUserDetails> getCurrentUser();
}
