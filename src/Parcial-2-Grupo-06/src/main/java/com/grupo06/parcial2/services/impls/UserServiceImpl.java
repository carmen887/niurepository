package com.grupo06.parcial2.services.impls;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.grupo06.parcial2.models.dto.UserInfoDTO;
import com.grupo06.parcial2.models.entities.User;
import com.grupo06.parcial2.repositories.UserRepository;
import com.grupo06.parcial2.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private PasswordEncoder passEncoder;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	@Transactional(rollbackOn = Exception.class)
	public void register(UserInfoDTO userInfo) throws Exception{
		User user = new User();
		
		String encryptedPassword = passEncoder.encode(userInfo.getPassword());
		
		user.setUsername(userInfo.getUsername());
		user.setEmail(userInfo.getEmail());
		user.setPassword(encryptedPassword);
		
		userRepository.save(user);
	}
	
	@Override
	public User findOneByUsernameAndEmail(String username, String email) throws Exception {
		User foundUser = userRepository	
				.findOneByUsernameOrEmail(username, email);
		
		return foundUser;
	}
}
