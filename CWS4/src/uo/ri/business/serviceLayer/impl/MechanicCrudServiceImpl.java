package uo.ri.business.serviceLayer.impl;

import java.util.List;

import uo.ri.business.dto.MechanicDto;
import uo.ri.business.serviceLayer.MechanicCrudService;
import uo.ri.business.transactionScripts.administrator.AddMechanic;
import uo.ri.business.transactionScripts.administrator.DeleteMechanic;
import uo.ri.business.transactionScripts.administrator.ListMechanics;
import uo.ri.business.transactionScripts.administrator.UpdateMechanic;
import uo.ri.common.BusinessException;

public class MechanicCrudServiceImpl implements MechanicCrudService {

	@Override
	public void addMechanic(MechanicDto mechanic) throws BusinessException {
		AddMechanic addMechanic = new AddMechanic(mechanic);
		addMechanic.execute();

	}

	@Override
	public void deleteMechanic(Long idMecanico) throws BusinessException {
		DeleteMechanic deleteMechanic = new DeleteMechanic(idMecanico);
		deleteMechanic.execute();

	}

	@Override
	public void updateMechanic(MechanicDto mecanico) throws BusinessException {
		UpdateMechanic updateMechanic = new UpdateMechanic(mecanico);
		updateMechanic.execute();
	}

	@Override
	public MechanicDto findMechanicById(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MechanicDto> findAllMechanics() throws BusinessException {
		ListMechanics listMechanics = new ListMechanics();
		return listMechanics.execute();
	}

	@Override
	public List<MechanicDto> findActiveMechanics() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
