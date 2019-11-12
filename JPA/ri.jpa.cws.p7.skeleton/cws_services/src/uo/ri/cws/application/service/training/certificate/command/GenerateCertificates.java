package uo.ri.cws.application.service.training.certificate.command;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CertificateRepository;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.repository.VehicleTypeRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Certificate;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;

public class GenerateCertificates implements Command<Integer> {
	private VehicleTypeRepository vehicleTypeRepository = Factory.repository.forVehicleType();
	private MechanicRepository mechanicRepository = Factory.repository.forMechanic();
	private CertificateRepository certificateRepository = Factory.repository.forCertificate();
	private CourseRepository courseRepository = Factory.repository.forCourse();

	@Override
	public Integer execute() throws BusinessException {
		int numCertificates = 0;
		List<VehicleType> vehicleTypes = vehicleTypeRepository.findAll();
		List<Mechanic> mechanics = mechanicRepository.findAll();

		for (VehicleType vehicleType : vehicleTypes) {
			for (Mechanic mechanic : mechanics) {
				if (!certificateRepository.findCertificateFor(mechanic, vehicleType).isPresent()) {
					Optional<BigDecimal> hours = courseRepository.findCourseHoursForCertificate(mechanic, vehicleType);
					if (hours.isPresent()
							&& hours.get().toBigInteger().intValue() >= vehicleType.getMinTrainingHours()) {
						certificateRepository.add(new Certificate(mechanic, vehicleType));
						numCertificates++;
					}
				}
			}
		}
		return numCertificates;
	}
}