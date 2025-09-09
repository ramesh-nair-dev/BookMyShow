package bookMyShow.demo.models;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class ShowSeat extends BaseClass{
    @ManyToOne
    private Show show;
    @ManyToOne
    private Seat seat;
    @Enumerated(EnumType.STRING)
    private ShowSeatStatus showSeatStatus;
    private Date blockedAt;
}

/**
 * What is ShowSeat?
 * - ShowSeat is a class that represents the seat of a show.
 * - It contains the show, seat and showSeatStatus.
 * * Why do we need ShowSeat?
 *  -  We need ShowSeat to keep track of the seat of a show.
 *  -  It is used to book a seat for a show.
 *  - It is used to check the availability of a seat for a show.
 *  -  It is used to get the details of a seat for a show.
 *
 *  Let's define the relationships:
 *
 * 1. ShowSeat and Show : One ShowSeat can have only one Show, but one Show can have multiple ShowSeats,we have ManyToOne relationship here.
 *                        For example : showSeat 1 object has 25b seat and status is available --> We can see that one ShowSeat can have only one Show like Show 1, Show 2, Show 3 etc.
 *                                      ShowSeat 1 object has 25c seat and status is available --> We can show id can have multiple showSeats like showSeats
 * 2. ShowSeat and Seat : One showSeat can have only one Seat , but one Seat can be part of multple Show , we have ManyToOne relationship here.
 *                        For example : Show 2 25b empty => Seat 25b
 *                                      Show 1 25b empty -> seat 25b
 *                        So, we have One showSeat object which has Show id 1 , Seat id 25b and status is available it mapped to physical seat 25b in the theater.
 *                        we have another showSeat object which has Show id 2, Seat id 25b and status is available it mapped to physical seat 25b in the theater.
 *                        we can make conclusion that one ShowSeat can have only one Seat , but that one seat can be part of multiple ShowSeats in this exmaple we
 *                        can see that Seat 25b is part of two ShowSeats, one for Show 1 and one for Show 2.
 */




