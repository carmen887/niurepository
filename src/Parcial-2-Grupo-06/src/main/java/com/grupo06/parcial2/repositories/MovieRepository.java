package com.grupo06.parcial2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo06.parcial2.models.entities.Category;
import com.grupo06.parcial2.models.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	List<Movie> findByCategory(Category category);
}
