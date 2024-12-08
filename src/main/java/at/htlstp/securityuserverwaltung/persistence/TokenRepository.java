package at.htlstp.securityuserverwaltung.persistence;

import at.htlstp.securityuserverwaltung.config.auth.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<VerificationToken, Long> {

    @Query("select v from VerificationToken v where v.token = ?1")
    Optional<VerificationToken> findVerificationToken(String token);
}
