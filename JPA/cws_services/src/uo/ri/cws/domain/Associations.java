package uo.ri.cws.domain;

public class Associations {

	public static class Own {

		public static void link(Client client, Vehicle vehicle) {
			vehicle._setClient(client);
			client._getVehicles().add(vehicle);
		}

		public static void unlink(Client client, Vehicle vehicle) {
			client._getVehicles().remove(vehicle);
			vehicle._setClient(null);
		}
		
	}

	public static class Classify {

		public static void link(VehicleType vehicleType, Vehicle vehicle) {
			vehicleType._getVehicles().add(vehicle);
			vehicle._setVehicleType(vehicleType);
		}

		public static void unlink(VehicleType vehicleType, Vehicle vehicle) {
			vehicleType._getVehicles().remove(vehicle);
			vehicle._setVehicleType(null);
		}
		

	}

	public static class Pay {

	}

	public static class Order {

		public static void link(Vehicle vehicle, WorkOrder workOrder) {
			workOrder._setVehicle(vehicle);
			vehicle._getWorkOrders().add(workOrder);
			
		}

		public static void unlink(Vehicle vehicle, WorkOrder workOrder) {
			vehicle._getWorkOrders().remove(workOrder);
			workOrder._setVehicle(null);
		}

	}

	public static class ToInvoice {

		public static void link(Invoice invoice, WorkOrder workOrder) {
			workOrder._setInvoice(invoice);
			invoice._getWorkOrders().add(workOrder);	
		}

	}

	public static class Charges {

	}

	public static class Assign {

		public static void link(Mechanic mechanic, WorkOrder workOrder) {
			workOrder._setMechanic(mechanic);
			mechanic._getWorkorders().add(workOrder);
			
		}

		public static void unlink(Mechanic mechanic, WorkOrder workOrder) {
			mechanic._getWorkorders().remove(workOrder);
			workOrder._setMechanic(null);	
		}

	}

	public static class Intervene {

		public static void link(WorkOrder workOrder, Intervention intervention, Mechanic mechanic) {
			intervention._setWorkOrder(workOrder);
			intervention._setMechanic(mechanic);
			
			intervention.getWorkOrder()._getInterventions().add(intervention);
			intervention.getMechanic()._getInterventions().add(intervention);
		}
		
		public static void unlink(Intervention intervention) {
			intervention.getMechanic()._getInterventions().remove(intervention);
			intervention.getWorkOrder()._getInterventions().remove(intervention);
			intervention._setWorkOrder(null);
			intervention._setMechanic(null);
			

		}

	}

	public static class Sustitute {

	}

}