package uo.ri.cws.application.service.mechanic.crud.command;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;

public class AddMechanic implements Command<MechanicDto> {

	private MechanicDto dto;
	private MechanicRepository mechanicRepository = Factory.repository.forMechanic();

	public AddMechanic(MechanicDto mecanico) {
		this.dto = mecanico;
	}

	@Override
	public MechanicDto execute() throws BusinessException {

		BusinessCheck.isTrue(dto.dni.trim().length() > 0, "DNI is blank");
		BusinessCheck.isTrue(!mechanicRepository.findByDni(dto.dni).isPresent(), "Mechanic already exists");

		Mechanic mechanic = new Mechanic(dto.dni, dto.name, dto.surname);
		mechanicRepository.add(mechanic);

		mechanic = mechanicRepository.findByDni(dto.dni).get();

		dto.id = mechanic.getId();

		return dto;
	}

}
