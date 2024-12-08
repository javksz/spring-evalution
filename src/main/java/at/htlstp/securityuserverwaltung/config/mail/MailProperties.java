package at.htlstp.securityuserverwaltung.config.mail;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "mail")
public record MailProperties(String address, String password) {
}
