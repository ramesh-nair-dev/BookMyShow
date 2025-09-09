package bookMyShow.demo.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpResponseDTO {
    private int userId;
    private ResponseStatus responseStatus;
    private String message;
}

/**
 * In response when user have successfully signed up we will send userId, responseStatus and message.
 */




