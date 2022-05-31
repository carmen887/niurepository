package com.grupo06.parcial2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo06.parcial2.models.entities.Booking;
import com.grupo06.parcial2.models.entities.Schedule;
import com.grupo06.parcial2.models.entities.User;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	List<Booking> findByUser(User user);
	Booking findOneByUserAndSchedule(User user, Schedule schedule);
}
