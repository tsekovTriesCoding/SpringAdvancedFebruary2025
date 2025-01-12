package bg.softuni.mobilelele.config;

import bg.softuni.mobilelele.repository.UserRepository;
import bg.softuni.mobilelele.service.impl.MobileleleUserDetailsServiceImpl;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeRequests -> {
                    authorizeRequests
                            //all static resources to common locations (css,images, js....)
                            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                            //more resources for all users
                            .requestMatchers("/", "/users/login", "/users/register", "/api/convert").permitAll()
                            //all other urls should be authenticated
                            .anyRequest().authenticated();
                })
                .formLogin(formLogin ->
                        formLogin
                                //Where is our custom login form
                                .loginPage("/users/login")
                                //what is the name of the username parameter
                                .usernameParameter("email")
                                //what is the name of the password parameter
                                .passwordParameter("password")
                                //what will happen if successful
                                .defaultSuccessUrl("/", true)
                                //what will happen if not successful
                                .failureForwardUrl("/users/login-error"))
                .logout(logout ->
                        logout
                                .logoutUrl("/users/logout")
                                .logoutSuccessUrl("/")
                                .invalidateHttpSession(true))
                .build();
    }

    //I can annotate MobileleleUserDetailsServiceImpl with @Service and remove the bean from here or do it like this but no
    // @Service annotation in the class!!!
    @Bean
    public MobileleleUserDetailsServiceImpl userDetailsService(UserRepository UserRepository) {
        return new MobileleleUserDetailsServiceImpl(UserRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }
}
