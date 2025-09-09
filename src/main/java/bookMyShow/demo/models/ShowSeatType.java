package bookMyShow.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class ShowSeatType extends BaseClass{
    @ManyToOne
    private Show show;
    @ManyToOne
    private SeatType seatType;
    private int price;

}

/**
 * This class represents the type of seat in a show.
 * It extends the BaseClass which contains common fields like id, created_at, and updated_at.
 * why we need ShowSeatType?
 * - we need this to decide the price of seat in a show. how we the price of seat in show is decided?
 * - a show at 5 pm will have different price for different seat types like Gold, Silver, Platinum etc.
 * - a show at 9 pm will have different price for different seat types like Gold, Silver, Platinum etc.
 * so price decide based on show time + seat type.
 *
 * let's define the relationships:
 *
 * 1. ShowSeatType and Show : One ShowSeatType can have only one Show, but one Show can have multiple ShowSeatTypes, we have ManyToOne relationship here.
 *                            for example : show 1 seat type Gold with price 500
 *                                          show 1 seat type Silver with price 300
 *                                          show 1 seat type Platinum with price 700
 *                            in the above all are showSeatType object are part of show 1, but show 1 can have multiple ShowSeatTypes like Gold, Silver, Platinum etc.
 *
 * 2. ShowSeatType and SeatType : One ShowSeatType can have only one SeatType, but one SeatType can be part of multiple ShowSeatTypes, we have ManyToOne relationship here.
 *                                for example : showSeatType 1 has seat type Gold with price 500 -- > seatype gold
 *                                              showSeatType 2 has seat type Gold with price 300 ---> seatype Gold
 *                                in this example we can see here we have two showSeatType object that belong to same SeatType Gold,
 *                                but that SeatType Gold can be part of multiple Shows
 *
 *
 */
