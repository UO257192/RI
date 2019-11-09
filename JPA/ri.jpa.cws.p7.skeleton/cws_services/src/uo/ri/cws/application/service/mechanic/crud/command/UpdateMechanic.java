package uo.ri.cws.application.service.mechanic.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;

public class UpdateMechanic implements Command<Void> {

	private MechanicDto dto;
	private MechanicRepository mechanicRepository = Factory.repository.forMechanic();

	public UpdateMechanic(MechanicDto dto) {
		this.dto = dto;
	}

	@Override
	public Void execute() throws BusinessException {
		BusinessCheck.isNotNull(dto);
		BusinessCheck.isTrue(dto.dni.trim().length() > 0, "DNI is blank");
		Optional<Mechanic> omechanic = mechanicRepository.findByDni(dto.dni);
		BusinessCheck.isTrue(omechanic.isPresent(), "El mecanico no existe");
		Mechanic mechanic = omechanic.get();
		mechanic.setName(dto.name);
		mechanic.setSurname(dto.surname);
		return null;
	}

}
