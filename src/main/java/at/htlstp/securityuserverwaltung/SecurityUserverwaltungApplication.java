package at.htlstp.securityuserverwaltung;

import at.htlstp.securityuserverwaltung.config.mail.MailProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication

@EnableConfigurationProperties({MailProperties.class})
public class SecurityUserverwaltungApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityUserverwaltungApplication.class, args);
    }

}