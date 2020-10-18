package pl.sda.auctions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDTO {
    @Size(min = 3)
    private String name;

    @Email
    private String email;

    @Size(min = 6)
    private String password;

    @Size(min = 6)
    private String repeatPassword;
}
