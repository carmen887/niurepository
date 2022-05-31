package com.grupo06.parcial2.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.grupo06.parcial2.models.entities.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
	@Query("SELECT s FROM schedule s where DATE(timestamp) = :date")
	//@Param("date")
	List<Schedule> findByTimestamp(@Param("date") Date date);
	Schedule findOneById(Long id);
}
