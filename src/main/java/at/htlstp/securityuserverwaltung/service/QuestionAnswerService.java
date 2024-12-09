package at.htlstp.securityuserverwaltung.service;

import at.htlstp.securityuserverwaltung.config.auth.AppUser;
import at.htlstp.securityuserverwaltung.domain.*;
import at.htlstp.securityuserverwaltung.persistence.QuestionAnswerRepository;
import org.springframework.stereotype.Service;

@Service
public record QuestionAnswerService(QuestionAnswerRepository questionAnswerRepository) {

    public QuestionAnswer saveAnswer(AppUser user, Question question, Answer answer) {
        QuestionAnswer questionAnswer = new QuestionAnswer();
        questionAnswer.setUser(user);
        questionAnswer.setQuestion(question);
        questionAnswer.setAnswer(answer);
        return questionAnswerRepository.save(questionAnswer);
    }


}
