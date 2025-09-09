package bookMyShow.demo.Service;

import bookMyShow.demo.Repository.BookingRepository;
import bookMyShow.demo.Repository.ShowRepository;
import bookMyShow.demo.Repository.ShowSeatRepository;
import bookMyShow.demo.Repository.UserRepository;
import bookMyShow.demo.models.*;
//import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final ShowSeatRepository showSeatRepository;
    private final UserRepository userRepository;
    private final ShowRepository showRepository;
    private final BookingRepository bookingRepository;
    private final PriceCalculatorService priceCalculatorService;

    @Autowired
    public BookingService(ShowSeatRepository showSeatRepository, UserRepository userRepository, ShowRepository showRepository , BookingRepository bookingRepository, PriceCalculatorService priceCalculatorService) {
        this.showSeatRepository = showSeatRepository;
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.bookingRepository = bookingRepository;
        this.priceCalculatorService = priceCalculatorService;
    }


    public Booking bookTickets(
            int userId,
            List<Integer> showSeatId,
            int  showId
    ){
        // Step 1: Get the user from the user repository

        Booking booking = new Booking();

        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            throw new RuntimeException("User not found with ID: " + userId);
        }

        User user = optionalUser.get();



        // Step 2: Get the show id from the show repository

        Optional<Show> optionalShow = showRepository.findById(showId);
        if(optionalShow.isEmpty()){
            throw new RuntimeException("Show not found with ID: " + showId);
        }

        Show show = optionalShow.get();


        // Step 3 : Get the show seats from the show seat repository

        List<ShowSeat> showSeatList = showSeatRepository.findAllById(showSeatId);

        if (showSeatList.isEmpty()) {
            throw new RuntimeException("No show seats found for the provided IDs.");
        }


        return confirmBooking(user , show ,showSeatList);

    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking confirmBooking(User user ,Show show ,List<ShowSeat> showSeatList){


        for(ShowSeat showSeat : showSeatList){
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)){
                throw new RuntimeException("Show seat with ID: " + showSeat.getId() + " is not available.");
            }
        }

        for (ShowSeat showSeat : showSeatList) {
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            showSeat.setBlockedAt(new Date());
            showSeatRepository.save(showSeat);
        }

        Booking booking = new Booking();
        booking.setBookedBy(user);
        booking.setBookingDate(new Date());
        booking.setSelectedSeats(showSeatList);
        booking.setAmount(priceCalculatorService.calculatePrice(showSeatList, show.getId()));
        booking.setBookingStatus(BookingStatus.SUCCESSFUL);
        bookingRepository.save(booking);

        return booking;

    }
}

/**
 * This class represents the BookingService in the BookMyShow application.
 *
 * So to book a seat of particular show we need to know
 * - User ID: To identify the user making the booking.
 * - Show Seat List: A list of seats that the user wants to book for a specific show.
 * - Show ID: The unique identifier of the show for which the booking is being made.
 *
 * Now we have all the information we need to book a show and seat.
 *
 * 1. First we get all the showSeats details that user wants to book from our db
 * 2. Then we will check that if selected showSeats are available or not
 *         - If yes, we will book the showSeats for the user and return the booking details.
 *         - Suppose A user want to book seat 1 and seat 2 for a show while checking we came to know that only seat 1 is available
 *           in this case we won't boook the showSeats and we will throw an exception
 *         - If seat as avaiable we will mark status of seat as blocked once payment is done we mark it as booked
 *
 *
 * what we we doing in confirmBooking method?
 *
 * there can be scenarios like
 *  - User A want book seat 1 and seat 2 for a show
 *  - User B want book seat 2 and seat 3 for the same show
 *
 *  We can't allow both users to book seat 2 at the same time this will lead to inconsistency in our system
 *
 *  solution we can use locks that only one user can book the seat at a time
 *
 *  how booking happens in book my show ?
 *  - User A want book seat 1 and seat 2 for a show -> then are move to summary page where they confirm yes i want to proceed
 *    at this time system block the seat 1 and seat 2 for user A for 10 minutes , so that user can confirm it paying why wait for 10
 *    there can scene where user tries to pay but payment fails , then user tries again to book the same seat after five minutes
 *    payment success then we mark the seat as booked , even after 10 mins if user doesn't pay then we mark the seat as available again
 *
 *   - If we put lock at this stage where user comes and seat is blocked for 10 minutes then all user b will have to wait for 10 minutes
 *   so we can use optimistic locking here
 *    - What we will do is we will check if the seat is available or not
 *    - If available we will change the status of the seat to BLOCKED and set the blockedAt time , that's it changing the status
 *      will not take much time so we can allow other users to check the availability of the seat
 *    - In our scenario i want to book seat 1 and seat 2 for a show and user B want to book seat 2 and seat 3 for the same show
 *    *      - User A will check the availability of seat 1 and seat 2 , both are available so we will change the status of seat 1 and seat 2 to BLOCKED
 *           - User B will check the availability of seat 2 and seat 3 , seat 2 is blocked by user A so we will throw an exception
 */
