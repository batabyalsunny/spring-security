/**
 *
 */
package ml.bootcode.springsecurity.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sunnyb
 *
 */
@RestController
public class UserController {

	@GetMapping("/users")
	public String getAllUsers() {
		return "List of all users";
	}

	@GetMapping("/secured/users")
	public String getSecuredUsers() {
		return "List of secured users";
	}
}
