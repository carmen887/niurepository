package com.grupo06.parcial2.controllers;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo06.parcial2.models.dto.BookingInfoDTO;
import com.grupo06.parcial2.models.dto.BookingShowDTO;
import com.grupo06.parcial2.models.dto.MessageDTO;
import com.grupo06.parcial2.models.entities.Booking;
import com.grupo06.parcial2.models.entities.Movie;
import com.grupo06.parcial2.models.entities.Schedule;
import com.grupo06.parcial2.services.MovieServices;

@RestController
@RequestMapping("/cinema")
public class CinemaController {
	
	@Autowired
	MovieServices movieServices;
	
	@GetMapping("/movies")
	public ResponseEntity<List<Movie>> findAllMovies(){
		try {
			List<Movie> movies = movieServices.findAll();
			return new ResponseEntity<>(movies, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/movies/{category}")
	public ResponseEntity<List<Movie>> getMovieByCategory(@PathVariable("category") String category){
		try {
			
			List<Movie> foundMovies = movieServices.findByCategory(category);
			if (foundMovies == null) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(foundMovies, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/today")
	public ResponseEntity<List<Schedule>> getScheduleToday(){
		try {
			Date date = new Date(System.currentTimeMillis());
			List<Schedule> schedule = movieServices.findByDate(date);
			return new ResponseEntity<>(schedule, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/reserve")
	public ResponseEntity<MessageDTO> createReservation(@Valid BookingInfoDTO bookingInfo, BindingResult result){
		try {
			if(result.hasErrors()) {
				String errors = result.getAllErrors().toString();
						
				
				return new ResponseEntity<>(
						new MessageDTO("Hay errores: " + errors),
						HttpStatus.BAD_REQUEST
					);
			}
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			movieServices.reservate(timestamp, bookingInfo.getUsername(), Long.parseLong(bookingInfo.getCode()));
			return new ResponseEntity<>(
					new MessageDTO("Reservacion Registrada"),
					HttpStatus.CREATED
				);
		} catch (Exception e) {
			return new ResponseEntity<>(
						new MessageDTO("Error interno"),
						HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	@PostMapping("/booking")
	public ResponseEntity<List<Booking>> getAllBooking(@Valid BookingShowDTO bookingInfo, BindingResult result){
		try {
			if(result.hasErrors()) {
				String errors = result.getAllErrors().toString();
						
				
				return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST
					);
			}
			List<Booking> bookings = movieServices.showReservations(bookingInfo.getUsername());
			return new ResponseEntity<>(bookings, HttpStatus.CREATED
				);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/reserve")
	public ResponseEntity<MessageDTO> deleteReservation(@Valid BookingInfoDTO bookingInfo, BindingResult result){
		try {
			if(result.hasErrors()) {
				String errors = result.getAllErrors().toString();
						
				
				return new ResponseEntity<>(
						new MessageDTO("Hay errores: " + errors),
						HttpStatus.BAD_REQUEST
					);
			}
			movieServices.deleteReservation(bookingInfo.getUsername(), Long.parseLong(bookingInfo.getCode()));
			return new ResponseEntity<>(
					new MessageDTO("Reservacion Eliminada"),
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
