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

public class DeleteMechanic implements Command<Void> {

    private String mechanicId;
	private MechanicRepository mechanicRepository = Factory.repository.forMechanic();

    public DeleteMechanic(String idMecanico) {
        this.mechanicId = idMecanico;
    }

    @Override
    public Void execute() throws BusinessException {

        BusinessCheck.isTrue(mechanicId.trim().length() != 0);

        Mechanic mechanic = mechanicRepository.findByDni(mechanicId).get();
        BusinessCheck.isTrue(mechanic != null, "The mechanic dies not exists");
		mechanicRepository.remove(mechanic);
        return null;
    }

}
