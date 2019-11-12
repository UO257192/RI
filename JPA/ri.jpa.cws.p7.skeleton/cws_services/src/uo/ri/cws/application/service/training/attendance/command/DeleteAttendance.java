package uo.ri.cws.application.service.training.attendance.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.EnrollmentRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Enrollment;

public class DeleteAttendance implements Command<Void> {

	private String id;
	private EnrollmentRepository enrollmentRepository = Factory.repository.forEnrollment();

	public DeleteAttendance(String id) {
		this.id = id;
	}

	@Override
	public Void execute() throws BusinessException {
		Optional<Enrollment> oenrollment = enrollmentRepository.findById(id);
		BusinessCheck.isTrue(oenrollment.isPresent(), "No existe la asistencia registrada");
		enrollmentRepository.remove(oenrollment.get());
		return null;
	}
}
