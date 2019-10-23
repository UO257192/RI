package uo.ri.persistance.training;

import java.sql.Connection;
import java.util.List;

import uo.ri.business.dto.CourseDto;

public interface CourseGateway {

	/**
	 * Set the database connection
	 * 
	 * @param database connection
	 */
	void setConnection(Connection c);

	/**
	 * @param id of the course
	 * @return the dto for the course or null if there is none with the id
	 */
	CourseDto findCourseByID(Long courseID);
	
	
	
	List<CourseDto> findAll();

	void add(CourseDto dto);
	
	Long findMaxCourse();
	
	CourseDto findByName(String name);
	
	CourseDto findByCode(String code);
	
	void delete(Long id);
	
	void update(CourseDto dto);
}