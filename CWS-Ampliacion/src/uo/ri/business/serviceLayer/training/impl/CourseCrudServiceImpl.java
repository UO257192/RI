package uo.ri.business.serviceLayer.training.impl;

import java.util.List;

import uo.ri.business.dto.CourseDto;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.business.serviceLayer.training.CourseCrudService;
import uo.ri.business.transactionScripts.administrator.DeleteCourse;
import uo.ri.business.transactionScripts.administrator.FindAllCourses;
import uo.ri.business.transactionScripts.administrator.FindCourseByID;
import uo.ri.business.transactionScripts.administrator.ListVehicleTypes;
import uo.ri.business.transactionScripts.administrator.RegisterNewCourse;
import uo.ri.business.transactionScripts.administrator.UpdateCourse;
import uo.ri.common.BusinessException;

public class CourseCrudServiceImpl implements CourseCrudService {

	@Override
	public CourseDto registerNew(CourseDto dto) throws BusinessException {
		RegisterNewCourse a = new RegisterNewCourse(dto);
		return a.execute();
	}

	@Override
	public void updateCourse(CourseDto dto) throws BusinessException {
		UpdateCourse u = new UpdateCourse(dto);
		u.execute();

	}

	@Override
	public void deleteCourse(Long id) throws BusinessException {
		DeleteCourse a = new DeleteCourse(id);
		a.execute();

	}

	@Override
	public List<CourseDto> findAllCourses() throws BusinessException {
		FindAllCourses fa = new FindAllCourses();
		return fa.execute();
	}

	@Override
	public List<VehicleTypeDto> findAllVehicleTypes() throws BusinessException {
		ListVehicleTypes lv = new ListVehicleTypes();
		return lv.execute();
	}

	@Override
	public CourseDto findCourseById(Long cId) throws BusinessException {
		FindCourseByID as = new FindCourseByID(cId);
		return as.execute();
	}

}
