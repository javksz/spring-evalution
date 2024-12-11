package at.htlstp.securityuserverwaltung.persistence;

import at.htlstp.securityuserverwaltung.domain.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Long> {

}
