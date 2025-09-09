package bookMyShow.demo.models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseClass{
    private String seatNumber;
    private int rowNumber;
    private int columnNumber;
    @ManyToOne
    private SeatType seatType;

}

/**
 * Why do we need `seatNumber` even though we already have a `seatId` from BaseClass?
 *
 * - The `seatId` is a unique internal identifier used in the database. It's useful for persistence,
 *   but it doesn't provide any meaningful information to end users.
 *
 * - `seatNumber` (like "A1", "B5", etc.) is a user-facing identifier printed on tickets and shown in the UI.
 *   It represents the actual label you'd see on a seat in a theater.
 *
 * - For example, two seats in different theaters can both have `seatNumber = "A1"`,
 *   but their `seatId`s will still be globally unique due to the BaseClass `id`.
 *
 * - `seatNumber`, along with `rowNumber` and `columnNumber`, helps us place the seat in the
 *   layout grid of a specific auditorium, and it improves readability, debugging, and ticket display.
 *
 * Let's define the relationship
 *  1. Seat to SeaType : One Seat can have only one SeatType, but one SeatType can have multiple Seats , we have ManyToOne relationship here.
 *                       for example : Seat 1 is Regular Seat -- >  we can see that one seat can have only one type of seat like Regular, Premium, VIP etc.
 *                                     Seat 2 is Regular Seat --->  we can see that one seatType can have multiple seats like Seat 1, Seat 2, Seat 3 etc.
 * Summary:
 * `seatId` = internal, DB-level unique ID
 * `seatNumber` = external, user-facing label like "A1"
 */

