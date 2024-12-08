package at.htlstp.securityuserverwaltung.config.mail;


import at.htlstp.securityuserverwaltung.config.auth.AppUser;
import at.htlstp.securityuserverwaltung.config.auth.VerificationToken;
import at.htlstp.securityuserverwaltung.service.UserService;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public record RegistrationListener(UserService service)
        implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        AppUser user = event.getUser();
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = service.createVerificationToken(user, token);
        sendConfirmationMail(event, user, verificationToken);
    }

    private void sendConfirmationMail(OnRegistrationCompleteEvent event, AppUser user, VerificationToken verificationToken) {
        var recipientAddress = user.getEmail();
        var subject = "Registration Confirmation";
        var confirmationUrl = String.format(
                "http://localhost:8080%s/confirmRegistration?token=%s",
                event.getAppUrl(),
                verificationToken.getToken()
        );
        var message = """
                Registration successful.
                Please confirm your registration at %s.
                This link expires at %s.
                """.formatted(confirmationUrl, verificationToken.getExpiryDate()
        );

//        var email = new SimpleMailMessage();
//        email.setTo(recipientAddress);
//        email.setSubject(subject);
//        email.setText(message);
//        mailSender.send(email);
        System.out.println("confirmation: " +confirmationUrl);
        System.out.println("message: " +message);
    }
}