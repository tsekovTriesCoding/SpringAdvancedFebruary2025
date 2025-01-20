package bg.softuni.mobileleleoffers.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    UserDetails extractUserInformation(String jwtToken);
}
