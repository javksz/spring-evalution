package at.htlstp.securityuserverwaltung.presentation;

import at.htlstp.securityuserverwaltung.config.auth.AppUser;
import at.htlstp.securityuserverwaltung.config.auth.UserDto;
import at.htlstp.securityuserverwaltung.config.mail.OnRegistrationCompleteEvent;
import at.htlstp.securityuserverwaltung.persistence.TokenRepository;
import at.htlstp.securityuserverwaltung.persistence.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public record WebController(UserRepository userRepository,
                            TokenRepository tokenRepository,
                            PasswordEncoder passwordEncoder,
                            ApplicationEventPublisher eventPublisher) {
    @GetMapping("login")
    String getLogin() {
        return "login";
    }


    @GetMapping("home")
    String getHome(Authentication authentication, Model model) {
        var email = authentication.getName();
        var user = userRepository.findByEmail(email);
        model.addAttribute("user", user);
        model.addAttribute("email", email);
        return "home";
    }

    @GetMapping("admin/home")
    String getAdminHome(Authentication authentication, Model model) {
        return "admin-home";
    }

    @GetMapping("register")
    public String displayRegistration(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "register";
    }
    @PostMapping("register")
    public String registerUser(@Valid UserDto userDto,
                               BindingResult bindingResult,
                               HttpServletRequest request) {
        if (bindingResult.hasErrors())
            return "register";
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            bindingResult.addError(new ObjectError("userDto", "Email already registered."));
            return "register";
        }
        var encryptedPassword = passwordEncoder.encode(userDto.getPassword());
        var user = new AppUser(null, userDto.getEmail(),"USER", encryptedPassword);
        user = userRepository.save(user);
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, request.getLocale(), request.getContextPath()));
        return "redirect:/login";
    }

    @GetMapping("confirmRegistration")
    public String confirmRegistration(Model model, @RequestParam String token) {
        var verificationToken = tokenRepository.findVerificationToken(token);
        if (verificationToken.isEmpty()) {
            var message = "Invalid authentication token";
            model.addAttribute("message", message);
            return "error";
        }
        var user = verificationToken.get().getUser();
        var tokenExpired = verificationToken.get().getExpiryDate().isBefore(LocalDateTime.now());
        if (tokenExpired) {
            var message = "Expired authentication token";
            model.addAttribute("message", message);
            return "error";
        }

        userRepository.save(user);
        return "redirect:/login";
    }
}
