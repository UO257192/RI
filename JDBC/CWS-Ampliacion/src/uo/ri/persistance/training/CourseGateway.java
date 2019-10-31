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
	
	/**
	 * 
	 * @returnthe list of courses registered in the system. It might be an empty list if there is no course
	 */
	List<CourseDto> findAll();

	/**
	 * Insert a new Course into database
	 * @param course dto
	 */
	void add(CourseDto dto);
	
	/**
	 * Find the last course id generated
	 * @return course id
	 */
	Long findMaxCourse();
	
	/**
	 * Find course by name
	 * @param course name
	 * @return course dto
	 */
	CourseDto findByName(String name);
	
	/**
	 * Find course by code
	 * @param course code
	 * @return course dto
	 */
	CourseDto findByCode(String code);
	
	/**
	 * Delete Course
	 * @param course id
	 */
	void delete(Long id);
	
	/**
	 * Update Course 
	 * @param course dto
	 */
	void update(CourseDto dto);
}