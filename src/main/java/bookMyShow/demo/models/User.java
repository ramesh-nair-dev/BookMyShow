package bookMyShow.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "users")
public class User extends BaseClass{
    private String userName;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    @OneToMany
    private List<Booking> bookingList;
}


