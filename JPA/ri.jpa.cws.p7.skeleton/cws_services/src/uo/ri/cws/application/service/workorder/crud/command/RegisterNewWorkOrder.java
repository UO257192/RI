package uo.ri.cws.application.service.workorder.crud.command;

import java.util.Date;
import java.util.Optional;

import alb.util.date.Dates;
import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.VehicleRepository;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Vehicle;
import uo.ri.cws.domain.WorkOrder;

public class RegisterNewWorkOrder implements Command<WorkOrderDto> {

	private WorkOrderDto workOrderDto;
	private VehicleRepository vehicleRepository = Factory.repository.forVehicle();
	private WorkOrderRepository workOrderRepository = Factory.repository.forWorkOrder();

	public RegisterNewWorkOrder(WorkOrderDto workOrderDto) {
		this.workOrderDto = workOrderDto;
	}

	@Override
	public WorkOrderDto execute() throws BusinessException {
		BusinessCheck.isTrue(workOrderDto != null, "La workorder es null");
		BusinessCheck.isTrue(workOrderDto.description.trim().length() > 0, "Description is blank");
		Optional<Vehicle> ovehicle = vehicleRepository.findById(workOrderDto.vehicleId);
		BusinessCheck.isTrue(ovehicle.isPresent(), "El vehiculo no existe");
		Date date = Dates.today();
		Optional<WorkOrder> aux = workOrderRepository.findByVehicleAndDate(ovehicle.get(), date);
		BusinessCheck.isTrue(aux.isPresent(), "Ya existe una averia con esta fecha y para este vehiculo");
		WorkOrder workOrder = new WorkOrder(date, ovehicle.get(), workOrderDto.description);
		workOrderRepository.add(workOrder);
		workOrderDto.id = workOrder.getId();
		return workOrderDto;
	}
}
