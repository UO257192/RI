package uo.ri.cws.application.service.training.report.command;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CertificateRepository;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.repository.VehicleTypeRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.TrainingHoursRow;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FindTrainingByVehicleTypeAndMechanic implements Command<List<TrainingHoursRow>> {

    private VehicleTypeRepository vehicleTypeRepository = Factory.repository.forVehicleType();
    private MechanicRepository mechanicRepository = Factory.repository.forMechanic();
    private CourseRepository courseRepository = Factory.repository.forCourse();

    @Override
    public List<TrainingHoursRow> execute() throws BusinessException {
        List<TrainingHoursRow> trainingHoursRows = new ArrayList<>();
        List<VehicleType> vehicleTypes = vehicleTypeRepository.findAll();
        List<Mechanic> mechanics = mechanicRepository.findAll();
        for (VehicleType vehicleType : vehicleTypes) {
            for (Mechanic mechanic : mechanics) {
                Optional<BigDecimal> hours = courseRepository.findTrainingByVehicleTypeAndMechanic(mechanic,vehicleType);
                if(hours.isPresent()){
                    TrainingHoursRow trainingHoursRow = new TrainingHoursRow();
                    trainingHoursRow.mechanicFullName = mechanic.getName() + " " + mechanic.getSurname();
                    trainingHoursRow.vehicleTypeName = vehicleType.getNombre();
                    trainingHoursRow.enrolledHours = hours.get().toBigInteger().intValue();
                    trainingHoursRows.add(trainingHoursRow);
                }

            }
        }
        return trainingHoursRows;
    }
}
