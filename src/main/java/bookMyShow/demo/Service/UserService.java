package bookMyShow.demo.Service;

import bookMyShow.demo.Repository.UserRepository;
import bookMyShow.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User signUp(
            String fName,
            String lName,
            String email ,
            String password
    ){

    // 1. Check if the user already exists with the same email.

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent()) {
            throw new RuntimeException("User already exists with email: " + email);
        }
    // 2. If no, we will create a new user.

        User user = new User();
        user.setFirstName(fName);
        user.setLastName(lName);
        user.setEmail(email);
        user.setPassword(password);
        user.setUserName("");
        user.setBookingList(new ArrayList<>());

    // 3. We will save the user in the database.


    return userRepository.save(user);

    }
}
/**
 * what is flow to sign up a user?
 *
 * 1. User will provide name, email and password.
 * 2. First we will check if the user already exists with the same email.
 *        - If yes, we will throw an exception.
 *        - If no, we will create a new user.
 * 3. We will save the user in the database.
 * 4. We will return the user object.
 *
 *
 * So to check if the user already exists, we will use the UserRepository.
 *
 */