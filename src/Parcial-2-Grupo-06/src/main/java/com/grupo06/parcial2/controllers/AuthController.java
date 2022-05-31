package com.grupo06.parcial2.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo06.parcial2.models.dto.MessageDTO;
import com.grupo06.parcial2.models.dto.UserInfoDTO;
import com.grupo06.parcial2.models.entities.User;
import com.grupo06.parcial2.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	UserService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<MessageDTO> registerUser(@Valid UserInfoDTO userInfo, BindingResult result) {
		try {
			if(result.hasErrors()) {
				String errors = result.getAllErrors().toString();
						
				
				return new ResponseEntity<>(
						new MessageDTO("Hay errores: " + errors),
						HttpStatus.BAD_REQUEST
					);
			}
			
			User foundUser = userService
					.findOneByUsernameAndEmail(userInfo.getUsername(), userInfo.getEmail());
			
			if(foundUser != null) {
				return new ResponseEntity<>(
						new MessageDTO("Este usuario ya existe"),
						HttpStatus.BAD_REQUEST
					);
			}
			
			userService.register(userInfo);
			
			return new ResponseEntity<>(
					new MessageDTO("Usuario Registrado"),
					HttpStatus.CREATED
				);
		} catch (Exception e) {
			return new ResponseEntity<>(
						new MessageDTO("Error interno"),
						HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
}
