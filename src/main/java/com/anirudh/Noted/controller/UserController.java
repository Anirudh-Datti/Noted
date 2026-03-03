import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.anirudh.Noted.service.UserService;
import com.anirudh.Noted.model.User;
import com.anirudh.Noted.service.JwtService;
@RestController  
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtService jwtService;
	
	@PostMapping("/register")
	public String register(@RequestBody User user) {
		
		return userService.saveUser(user);
		
		
	}
	
	@PostMapping("/login")
	public String login(@RequestBody User user) {
		
		Authentication authentication = authneticationManager
				.authenticate(new UserNamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		
		if (authentication.isAuthenticated()) 
			return jwtService.generateToken(user.getEmail());
		else 
			return "bad credentials";
		
	}
	
	
}