package com.grupo06.parcial2.services;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.grupo06.parcial2.models.entities.Booking;
import com.grupo06.parcial2.models.entities.Movie;
import com.grupo06.parcial2.models.entities.Schedule;

public interface MovieServices {
	List<Movie> findAll() throws Exception;
	List<Schedule> findByDate(Date date) throws Exception;
	List<Movie> findByCategory(String category) throws Exception;
	void reservate(Timestamp time, String username, Long code) throws Exception;
	List<Booking> showReservations(String username) throws Exception;
	void deleteReservation(String username, Long code) throws Exception;
}
