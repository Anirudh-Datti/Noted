
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.anirudh.Noted.model.User;
import com.anirudh.Noted.repo.UserRepo;

public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private BCryptPasswordEncoder encoder  = new BCryptPasswordEncoder(12);
	
	public User saveUser(User user) { 
		
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepo.save(user);
	}
}