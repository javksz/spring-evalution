package at.htlstp.securityuserverwaltung.persistence;

import at.htlstp.securityuserverwaltung.domain.Question;
import at.htlstp.securityuserverwaltung.domain.QuestionAnswer;
import at.htlstp.securityuserverwaltung.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Long> {

}
