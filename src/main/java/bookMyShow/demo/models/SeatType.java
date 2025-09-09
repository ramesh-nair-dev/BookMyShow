package bookMyShow.demo.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SeatType extends BaseClass{
    private String name;
    private String description;
}

/**
 * This is seatype class why are not making it enum?
 *
 * Let's Theatre A and this seat naming convention is: Silver , Gold, Diamond
 * Let;s Theatre B and this seat naming convention is: Gold , Diamond, Platinum
 *
 * So seatype is not fixed it depends on the threatre and their naming convention.
 */


