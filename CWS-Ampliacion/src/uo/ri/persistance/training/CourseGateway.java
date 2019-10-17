package uo.ri.persistance.training;

import java.sql.Connection;

import uo.ri.business.dto.CourseDto;

public interface CourseGateway {
	void setConnection(Connection c);

	CourseDto findCourseByID(Long courseID);
}
