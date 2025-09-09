package bookMyShow.demo.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingResponseDTO {
    private int bookingId;
    private String movieName;
    private int userId;
    private ResponseStatus responseStatus;
    private int amount;
    private String message;
}
