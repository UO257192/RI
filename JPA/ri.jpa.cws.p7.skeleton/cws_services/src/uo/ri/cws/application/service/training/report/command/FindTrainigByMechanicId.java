package uo.ri.cws.application.service.training.report.command;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.repository.VehicleTypeRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.TrainingForMechanicRow;
import uo.ri.cws.application.service.training.TrainingHoursRow;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FindTrainigByMechanicId implements Command<List<TrainingForMechanicRow>> {

    private VehicleTypeRepository vehicleTypeRepository = Factory.repository.forVehicleType();
    private MechanicRepository mechanicRepository = Factory.repository.forMechanic();
    private CourseRepository courseRepository = Factory.repository.forCourse();
    private String id;

    public FindTrainigByMechanicId(String id) {
        this.id = id;
    }

    @Override
    public List<TrainingForMechanicRow> execute() throws BusinessException {
        Optional<Mechanic> om = mechanicRepository.findById(id);
        BusinessCheck.isTrue(om.isPresent(), "El mecanico no existe");

        List<TrainingForMechanicRow> trainingForMechanicRows = new ArrayList<>();
        List<VehicleType> vehicleTypes = vehicleTypeRepository.findAll();
        Mechanic mechanic = om.get();

        for (VehicleType vehicleType : vehicleTypes) {
            Optional<BigDecimal> hoursAttended = courseRepository.findTrainingByVehicleTypeAndMechanic(mechanic, vehicleType);
            Optional<BigDecimal> hoursEnrolled = courseRepository.findEnrolledHoursByVehicleTypeAndMechanic(mechanic, vehicleType);
            if (hoursAttended.isPresent() && hoursEnrolled.isPresent()) {
                int hoursA = hoursAttended.get().toBigInteger().intValue();
                int hoursE = hoursEnrolled.get().toBigInteger().intValue();
                if (hoursA > 0 && hoursE > 0) {
                    TrainingForMechanicRow trainingForMechanicRow = new TrainingForMechanicRow();
                    trainingForMechanicRow.attendedHours = hoursA;
                    trainingForMechanicRow.enrolledHours = hoursE;
                    trainingForMechanicRow.vehicleTypeName = vehicleType.getNombre();
                    trainingForMechanicRows.add(trainingForMechanicRow);
                }
            }

        }
        return trainingForMechanicRows;
    }
}
