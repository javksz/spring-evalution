package at.htlstp.securityuserverwaltung.config.auth;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserDto {

    @NotBlank
    @Email
    private String email;

    @Pattern(regexp = "^.{1,8}$") // pw regex
    @NotBlank
    private String password;
    @NotBlank
    private String passwordRepeat;

    @AssertTrue(message = "Passwords must match")
    public boolean isValid() {
        return password.equals(passwordRepeat);
    }
}
