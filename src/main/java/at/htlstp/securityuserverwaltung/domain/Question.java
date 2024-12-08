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

    @ElementCollection(targetClass = Answer.class)
    @Enumerated(EnumType.STRING)
    @Size(min = 1, max = 1)
    private List<Answer> answers;

    @FutureOrPresent
    private LocalDateTime expirationDate;


}
