package bookMyShow.demo.Service;

import bookMyShow.demo.Repository.ShowSeatTypeRepository;
import bookMyShow.demo.models.SeatType;
import bookMyShow.demo.models.ShowSeat;
import bookMyShow.demo.models.ShowSeatType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriceCalculatorService {
    private final ShowSeatTypeRepository showSeatTypeRepository;

    @Autowired
    public PriceCalculatorService(ShowSeatTypeRepository showSeatTypeRepository){
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    public int calculatePrice(List<ShowSeat> showSeatList , int showId){

        int totalPrice = 0;

        for(ShowSeat showSeat : showSeatList){
            SeatType seatType = showSeat.getSeat().getSeatType();
            Optional <ShowSeatType> optionalShowSeatType = showSeatTypeRepository.findByShowAndSeatType(showId,seatType.getName());
            if(!optionalShowSeatType.isPresent()){
                throw new RuntimeException("Show Seat Type not found for showId: " + showId + " and seatType: " + seatType.getName());
            }
            ShowSeatType showSeatType = optionalShowSeatType.get();
            totalPrice += showSeatType.getPrice();
        }

        return totalPrice;

    }
}

/**
 * This service is responsible for calculating the total price of a booking based on the selected show seats and their types.
 *
 * What we are doing here is :
 * - We are getting the list of show seats from the booking request.
 * - For each show seat, we are getting the seat type and then getting the price of that seat type from the database.
 * - We are then adding the price of each seat type to the total price.
 * - Finally, we are returning the total price.
 */


