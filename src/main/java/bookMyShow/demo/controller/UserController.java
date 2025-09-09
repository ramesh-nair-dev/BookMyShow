package bookMyShow.demo.controller;

import bookMyShow.demo.Service.UserService;
import bookMyShow.demo.dtos.ResponseStatus;
import bookMyShow.demo.dtos.SignUpRequestDTO;
import bookMyShow.demo.dtos.SignUpResponseDTO;
import bookMyShow.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }
    public SignUpResponseDTO signUp(
            SignUpRequestDTO requestDTO
    ) {
        SignUpResponseDTO responseDTO = new SignUpResponseDTO();
        try{
            User user = userService.signUp(requestDTO.getFName(), requestDTO.getLName(), requestDTO.getEmail(), requestDTO.getPassword());
            responseDTO.setUserId(user.getId());
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            responseDTO.setMessage("User signed up successfully");

        } catch (Exception e) {
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
            responseDTO.setMessage("User sign up failed: " + e.getMessage());
        }
        return responseDTO;
    }
}

/**
 * We want to add sign up a user how we will do that?
 *
 * 1. The request will come to user controller user will provide the details name , email etc
 *
 * Inside UserController we we have function called signUp which will take SignUpRequestDTO as input in that object
 * we will have all the details of the user like name, email, password etc. we will also have service layer which do the
 * business logic and save the user in the database and send the response back to the controller and controller will send the
 * responseDTO back to the user.
 */
