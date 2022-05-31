package com.grupo06.parcial2.services;

import com.grupo06.parcial2.models.dto.UserInfoDTO;
import com.grupo06.parcial2.models.entities.User;

public interface UserService {
	void register(UserInfoDTO userInfo) throws Exception;
	User findOneByUsernameAndEmail(String username, String email) throws Exception;
}
