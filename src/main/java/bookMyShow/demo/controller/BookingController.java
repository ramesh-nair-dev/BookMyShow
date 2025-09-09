package bookMyShow.demo.controller;

import bookMyShow.demo.Service.BookingService;
import bookMyShow.demo.dtos.BookingRequestDTO;
import bookMyShow.demo.dtos.BookingResponseDTO;
import bookMyShow.demo.dtos.ResponseStatus;
import bookMyShow.demo.models.Booking;

public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    public BookingResponseDTO bookTicket(
            BookingRequestDTO requestDTO
    ){
        BookingResponseDTO responseDTO = new BookingResponseDTO();

        try{
            Booking booking = bookingService.bookTickets(requestDTO.getUserId(),requestDTO.getShowSeatList(),requestDTO.getShowId());
            responseDTO.setBookingId(booking.getId());
            responseDTO.setUserId(booking.getBookedBy().getId());
            responseDTO.setAmount(booking.getAmount());
            responseDTO.setMovieName(booking.getSelectedSeats().get(0).getShow().getMovie().getTitle());
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            responseDTO.setMessage("Booking successful for movie: " + booking.getSelectedSeats().get(0).getShow().getMovie().getTitle());

        } catch (Exception e) {
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
            responseDTO.setMessage("Booking failed: " + e.getMessage());
        }

        return responseDTO;
    }
}
