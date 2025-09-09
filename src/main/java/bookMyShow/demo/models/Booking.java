package bookMyShow.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@Entity
public class Booking extends BaseClass{
    @ManyToOne
    private User bookedBy;
    @OneToMany
    private List<ShowSeat> selectedSeats;
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;
    private int amount;
    @OneToMany
    private List<Payment> paymentList;
    private Date bookingDate;
}

/**
 /**
 * Booking class represents a user's booking of a specific show in the system.
 *
 * Attributes:
 *
 * 1. `bookedBy` — The user who made the booking.
 *
 * 2. `showDetails` — In a real-world system like BookMyShow, users don’t book a movie or a seat directly.
 *    They book a **specific show**, which is a combination of:
 *    - Movie
 *    - Theater
 *    - Screen
 *    - Show timing
 *    - Seats
 *    This is why we store `Show` details in the booking.
 *
 * 3. `selectedSeats` — A list of seats selected by the user *for that specific showtime*.
 *    Instead of `List<Seat>`, we use `List<ShowSeat>` because:
 *    - `Seat` represents a physical seat in a screen.
 *    - But a user doesn't book a seat for the entire day — they book it for a specific **show**.
 *    - So we use `ShowSeat`, which represents the mapping of a seat for a specific show.
 *
 * 4. `bookingStatus` — Represents the current status of the booking (e.g., CONFIRMED, PENDING, CANCELLED).
 *
 * 5. `amount` — The total price paid by the user for the selected seats.
 *
 * 6. `paymentList` — A list of all payment attempts made by the user for this booking.
 *    For example:
 *    - User tries UPI → fails
 *    - Then tries credit card → fails
 *    - Then tries net banking → succeeds
 *    We store all these attempts for tracking and audit purposes.
 *
 * 7. `bookingDate` — The date and time when the booking was made.
 *
 *
 * Let's define the cardinality of the relationships:
 *
 * 1. Booking to User : One Booking can be made by one User, but one User can make multiple Bookings.
 *                      This is a ManyToOne relationship.
 *                      For example:
 *                      - User 1 can have booking 1 → One user can make one booking at a time
 *                      - User 1 can have booking 2 → A single user can have multiple bookings
 *                      - User 2 can have booking 3
 *                      - User 3 and User 4 cannot share booking 4
 *
 * 2. Booking to ShowSeat : One Booking can have multiple ShowSeats, but one ShowSeat can belong to only one Booking.
 *                          This is a OneToMany relationship.
 *                          For example:
 *                          - Booking 1 can have ShowSeat 1, ShowSeat 2, ShowSeat 3
 *                            (Booking is done by one user, and that user selects multiple seats)
 *                          - Booking 2 can have ShowSeat 4, ShowSeat 5
 *                            (Another user books these seats, and no seat overlaps with Booking 1)
 *
 * 3. Booking to Payment : One Booking can have multiple Payments, but one Payment can belong to only one Booking.
 *                         This is a OneToMany relationship.
 *
 * 4. Booking to BookingStatus : BookingStatus is an enum, so we can't define cardinality here.
 *                               - In DB, we store it as a string, and each Booking has only one BookingStatus.
 *                               - For example: Booking 1 can have BookingStatus CONFIRMED, PENDING, CANCELLED, etc.
 *                               - In DB we don’t create a table for the enum; instead, we just store it as a string in the Booking table.
 *                               - We use @Enumerated(EnumType.STRING) to store the enum as a string in the database.
 *                               - We could also use @Enumerated(EnumType.ORDINAL) to store it as an integer,
 *                                 but that’s not recommended since it can cause issues if enum order changes later.
 *
 *
 * How EnumType.ORDINAL and EnumType.STRING work:
 *
 * - EnumType.ORDINAL stores enum values as integers in the DB, based on their order in the enum declaration.
 *   For example: Suppose BookingStatus enum is declared as: PENDING, CONFIRMED, CANCELLED
 *   → They get mapped to 0, 1, 2 respectively.
 *
 *   If Booking 1 has status = CONFIRMED, DB stores `1`.
 *   Now if we insert a new status REFUND before PENDING →
 *   REFUND=0, PENDING=1, CONFIRMED=2, CANCELLED=3.
 *   When we fetch Booking 1 again, it will incorrectly map to PENDING instead of CONFIRMED.
 *
 * - EnumType.STRING stores enum values as their names in DB, e.g., "CONFIRMED".
 *   This is more **readable, stable, and maintainable** even if enum order changes in the future.
 *
 */

