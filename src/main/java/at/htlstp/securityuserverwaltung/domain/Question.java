package at.htlstp.securityuserverwaltung.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String shortDescription;
    @Column(length = 200)
    private String longDescription;


    @OneToMany(mappedBy = "question")
    private List<QuestionAnswer> answers;

    @FutureOrPresent
    private LocalDateTime expirationDate;

    public long countAnswersByType(String answerType) {
        return answers.stream().filter(a -> a.getAnswer().name().equals(answerType)).count();
    }

}
