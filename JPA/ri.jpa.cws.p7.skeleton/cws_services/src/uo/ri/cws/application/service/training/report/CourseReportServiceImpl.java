package uo.ri.cws.application.service.training.report;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CertificateDto;
import uo.ri.cws.application.service.training.CourseReportService;
import uo.ri.cws.application.service.training.TrainingForMechanicRow;
import uo.ri.cws.application.service.training.TrainingHoursRow;
import uo.ri.cws.application.service.training.report.command.FindCertificatedByVehicleType;
import uo.ri.cws.application.service.training.report.command.FindTrainigByMechanicId;
import uo.ri.cws.application.service.training.report.command.FindTrainingByVehicleTypeAndMechanic;
import uo.ri.cws.application.util.command.CommandExecutor;

import java.util.List;

public class CourseReportServiceImpl implements CourseReportService {

    private CommandExecutor executor = Factory.executor.forExecutor();
    @Override
    public List<TrainingForMechanicRow> findTrainigByMechanicId(String id) throws BusinessException {
        return executor.execute(new FindTrainigByMechanicId(id));
    }

    @Override
    public List<TrainingHoursRow> findTrainingByVehicleTypeAndMechanic() throws BusinessException {
        return executor.execute(new FindTrainingByVehicleTypeAndMechanic());
    }

    @Override
    public List<CertificateDto> findCertificatedByVehicleType() throws BusinessException {
        return executor.execute(new FindCertificatedByVehicleType());
    }
}
