package bookMyShow.demo;

import bookMyShow.demo.controller.UserController;
import bookMyShow.demo.dtos.SignUpRequestDTO;
import bookMyShow.demo.dtos.SignUpResponseDTO;
import bookMyShow.demo.models.BaseClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private UserController userController;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// This method is called after the application context is loaded.
		// You can use it to perform any initialization tasks or run specific code.
		System.out.println("Application has started successfully!");



		SignUpRequestDTO requestDTO = new SignUpRequestDTO();
		requestDTO.setFName("Robb");
		requestDTO.setLName("Stark");
		requestDTO.setEmail("robbStark@gmail.com");
		requestDTO.setPassword("123456");

		SignUpResponseDTO responseDTO = userController.signUp(requestDTO);

		System.out.println(responseDTO.getMessage());
	}
}
