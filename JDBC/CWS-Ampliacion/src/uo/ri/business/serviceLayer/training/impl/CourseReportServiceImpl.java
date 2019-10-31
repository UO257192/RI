package uo.ri.business.serviceLayer.training.impl;

import java.util.List;

import uo.ri.business.dto.CertificateDto;
import uo.ri.business.dto.TrainingForMechanicRow;
import uo.ri.business.dto.TrainingHoursRow;
import uo.ri.business.serviceLayer.training.CourseReportService;
import uo.ri.business.transactionScripts.administrator.courseReport.ListMechanicTrainingHoursByVehicleType;
import uo.ri.business.transactionScripts.administrator.courseReport.ListTrainingForMechanic;
import uo.ri.business.transactionScripts.administrator.mechanic.ListMechanicsByVehicleType;
import uo.ri.common.BusinessException;

public class CourseReportServiceImpl implements CourseReportService {

	@Override
	public List<TrainingForMechanicRow> findTrainigByMechanicId(Long id) throws BusinessException {
		ListTrainingForMechanic a = new ListTrainingForMechanic(id);
		return a.execute();
	}

	@Override
	public List<TrainingHoursRow> findTrainingByVehicleTypeAndMechanic() throws BusinessException {
		ListMechanicTrainingHoursByVehicleType list = new ListMechanicTrainingHoursByVehicleType();
		return list.execute();
	}

	@Override
	public List<CertificateDto> findCertificatedByVehicleType() throws BusinessException {
		ListMechanicsByVehicleType as = new ListMechanicsByVehicleType();
		return as.execute();
	}

}
