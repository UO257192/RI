package uo.ri.cws.application.service.mechanic.crud.command;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Optional;

public class DeleteMechanic implements Command<Void> {

    private String mechanicId;
	private MechanicRepository mechanicRepository = Factory.repository.forMechanic();

    public DeleteMechanic(String idMecanico) {
        this.mechanicId = idMecanico;
    }

    @Override
    public Void execute() throws BusinessException {

        BusinessCheck.isTrue(mechanicId.trim().length() != 0);

        Optional<Mechanic> omechanic = mechanicRepository.findByDni(mechanicId);
        BusinessCheck.isTrue(omechanic.isPresent(), "El mecanico no existe");
		mechanicRepository.remove(omechanic.get());
        return null;
    }

}
