package bookMyShow.demo.models;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;



public enum ShowSeatStatus {
    BOOKED,
    AVAILABLE,
    OUT_OF_SERVICE,
    BLOCKED
}
