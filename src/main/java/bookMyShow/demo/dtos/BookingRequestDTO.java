package bookMyShow.demo.dtos;

import bookMyShow.demo.models.ShowSeat;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class BookingRequestDTO {
    private int userId;
    private List<Integer> showSeatList;
    private int showId;
}

/**
 * This class represents a booking request in the BookMyShow application.
 *
 * When a user want to book a show and seat , what all information is required
 * - User ID: To identify the user making the booking.
 * - Show Seat List: A list of seats that the user wants to book for a specific show.
 * - Show ID: The unique identifier of the show for which the booking is being made.
 *
 * all these details we can get from the frontend when user is trying to book a show and seat.
 */
