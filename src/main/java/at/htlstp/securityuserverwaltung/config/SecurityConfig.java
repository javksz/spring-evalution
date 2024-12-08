package at.htlstp.securityuserverwaltung.config;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.security.SecureRandom;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Entwicklung:
 * standard
 * standard mit eigenem User
 * basicAuth
 * rest - nur read geht
 * csrf disable
 * role
 * authority
 * preAuthorize
 * Cross Site Request Forgery
 * csrf disable nur für pure services ohne browser
 * basic -> formLogin
 * remember-me (Cookies löschen)
 */
@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        var passwordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
        System.out.println("pw: " + passwordEncoder.encode("pw"));
        // System.out.println("123: " + passwordEncoder.encode("123"));
        // System.out.println("pw: " + passwordEncoder.encode("pw"));
        return passwordEncoder;}

    @Bean
    public AuthenticationProvider authenticationManager(
            PasswordEncoder passwordEncoder,
            UserDetailsService userDetailsService) {
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        return usual(http);
    }

    private static DefaultSecurityFilterChain standard(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .build();
    }

    private SecurityFilterChain free(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .formLogin(AbstractHttpConfigurer::disable)
                .build();
    }

    private SecurityFilterChain usual(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth ->
                        auth    // Reihenfolge wichtig! oben > unten
                                .requestMatchers(GET, "/", "/home", "/login", "/register").permitAll()
//                                .requestMatchers("/greeting/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/home/**").hasRole("USER")
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/**", "/*").authenticated() // anyRequest
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .successHandler(customAuthenticationSuccessHandler()))
                .build();
    }

    private SecurityFilterChain apiBasicAuth(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable) //no generated views, no sessions
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/", "/home").permitAll()
                                .requestMatchers("/admin/**").hasRole("admin")
                                .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                .build();
    }

    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return (request, response, authentication) -> {
            var authorities = authentication.getAuthorities();
            var redirectUrl = authorities.stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN")) ? "/admin/home" : "/home";
            response.sendRedirect(redirectUrl);
        };
    }

}
