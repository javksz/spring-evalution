package at.htlstp.securityuserverwaltung.service;

import at.htlstp.securityuserverwaltung.config.auth.AppUser;
import at.htlstp.securityuserverwaltung.config.auth.VerificationToken;
import at.htlstp.securityuserverwaltung.persistence.TokenRepository;
import at.htlstp.securityuserverwaltung.persistence.UserRepository;
import org.springframework.stereotype.Service;

@Service
public record UserService(UserRepository userRepository, TokenRepository tokenRepository) {

    public VerificationToken createVerificationToken(AppUser user, String token) {
        var verificationToken = new VerificationToken(user, token);
        return tokenRepository.save(verificationToken);
    }
}
