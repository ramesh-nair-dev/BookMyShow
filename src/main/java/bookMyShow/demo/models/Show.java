package bookMyShow.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Getter
@Setter
@Entity(name="shows")
public class Show extends BaseClass{
    @ManyToOne
    private Movie movie;
    private Date startTime;
    private int duration;
    @ManyToOne
    private Screen screen;
    @OneToMany
    private List<ShowSeat> showSeatList;
    @OneToMany
    private List<ShowSeatType> showSeatTypeList;
}


/**
 * The `Show` class represents a scheduled screening of a specific movie in a particular theater.
 *
 * It includes the following fields:
 *
 * 1. `movie`: The movie being shown in this particular show.
 *
 * 2. `theater`: The theater where the show is taking place.
 *
 * 3. `startTime`: The exact time the show begins.
 *
 * 4. `duration`: Duration of the show in minutes.
 *
 * 5. `screen`: The specific screen in the theater where this show will be played.
 *
 * 6. `showSeatList`: A list of `ShowSeat` objects that represent the seats available for this show.
 *
 *    ❓ Why not store `List<Seat>` directly?
 *
 *    While it might seem intuitive to store `List<Seat>` (since seats physically belong to a screen),
 *    doing so would cause a **design flaw**:
 *
 *    - The `Seat` class represents **a physical seat** (static data): seat ID, row, column, etc.
 *    - However, **availability (booked or not)** is dynamic and **varies per show**.
 *
 *    If you were to modify the `Seat` object to track availability (e.g., `seatStatus`), you'd run into issues like:
 *
 *    - One show at 9:00 PM might mark Seat ID 1 as booked.
 *    - Another show at 10:00 PM using the same seat would **incorrectly inherit the booked status**.
 *
 *    This violates the principle of separation of concerns.
 *
 *    ✅ Solution:
 *    The `ShowSeat` class is introduced to represent **seat status in the context of a specific show**.
 *    It acts as a bridge between `Seat` and `Show`, and holds:
 *      - Reference to the seat
 *      - Booking status (available/booked)
 *      - Possibly booking info (who booked, at what time)
 *
 * 7. `showSeatTypeList`: A list of `ShowSeatType` objects that map seat types (e.g., Regular, Premium)
 *    to their pricing for this specific show.
 *
 *    ❓ Why a separate class for this?
 *    - Prices can change dynamically based on:
 *        - Seat type (Regular, Premium, VIP)
 *        - Time of the show (morning, evening, weekend)
 *    - Therefore, `ShowSeatType` is used to **store price configurations specific to this show**,
 *      avoiding hardcoding prices in the static `SeatType` enum/class.
 *
 * Let's define the relationships:
 *
 * 1. Show to Movie : One Show can have only one Movie, but one Movie can have multiple Shows, we have ManyToOne relationship here.
 * 2. Show to Screen : One Show can have only one Screen, but one Screen can have multiple Shows, we have ManyToOne relationship here.
 * 3. Show to ShowSeat : One Show can have multiple ShowSeats, but one ShowSeat can belong to only one Show, we have OneToMany relationship here.
 * 4. Show to ShowSeatType : One Show can have multiple ShowSeatTypes, but one ShowSeatType can belong to only one Show, we have OneToMany relationship here.
 *
 */

