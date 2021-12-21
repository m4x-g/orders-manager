package org.example.ordersmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan(basePackages = "org.example.ordersmanager.data.i18n")
@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
public class OrdersManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdersManagerApplication.class, args);
	}

//	@Bean
//	CommandLineRunner commandLineRunner(UserRepository userRepository, OrderRepository orderRepository) {
//		return args -> {
//			User johnDoe = new User("John Doe 2", "password", "nowhere", "user");
//			User janeDoe = new User("Jane Doe 2", "password", "somewhere", "operator");
//			User orgalorg = new User("orgalorg 2", "password", "everywhere", "admin");
//			userRepository.save(johnDoe);
//			userRepository.save(janeDoe);
//			userRepository.save(orgalorg);
//
//			orderRepository.save(new Order(new BigDecimal("19.95"), "pending", new Date(), janeDoe));
//		};
//	}
}
