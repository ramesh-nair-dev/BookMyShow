package bookMyShow.demo.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDTO {
    private String fName;
    private String lName;
    private String email;
    private String password;
}


/**
 * What we details we need to sign up a user?
 * - Name
 * - email
 * - password
 * only for this scenario we are taking these three details.
 */