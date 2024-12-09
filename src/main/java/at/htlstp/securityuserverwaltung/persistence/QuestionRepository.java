package at.htlstp.securityuserverwaltung.persistence;

import at.htlstp.securityuserverwaltung.domain.Answer;
import at.htlstp.securityuserverwaltung.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("select q from Question q where  q.expirationDate > ?1")
    public List<Question> findAllByAnswersBeforeExpirationDate(LocalDateTime expirationDate);

    @Query("SELECT q FROM Question q WHERE q.expirationDate > :currentDate AND q.id NOT IN " +
            "(SELECT qa.question.id FROM QuestionAnswer qa WHERE qa.user.id = :userId)")
    List<Question> findUnansweredQuestionsByUser(@Param("userId") Long userId, @Param("currentDate") LocalDateTime currentDate);

}
