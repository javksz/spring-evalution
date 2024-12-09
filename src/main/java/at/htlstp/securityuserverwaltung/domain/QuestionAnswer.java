package at.htlstp.securityuserverwaltung.domain;

import at.htlstp.securityuserverwaltung.config.auth.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "question_answers")
public class QuestionAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Answer answer;
}
