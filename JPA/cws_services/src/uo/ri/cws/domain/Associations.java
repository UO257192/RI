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
		public static void link(Client client, PaymentMean paymentMean) {
			paymentMean._setClient(client);
			client._getPaymentMeans().add(paymentMean);
		}
		
		public static void unlink(Client client, PaymentMean paymentMean) {
			client._getPaymentMeans().remove(paymentMean);
			paymentMean._setClient(null);
		}

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
		public static void unlink(Invoice invoice, WorkOrder workOrder) {
			invoice._getWorkorders().remove(workOrder);
			workOrder._setInvoice(null);
		}

	}

	public static class Charges {
		public static void link(PaymentMean paymentMean, Charge chars, Invoice invoice) {
			chars._setPaymentMean(paymentMean);
			chars._setInvoice(invoice);
			paymentMean._getCharges().add(chars);
			invoice._getCharges().add(chars);
		}

		public static void unlink(Charge charge) {
			PaymentMean mp = charge.getPaymentMean();
			Invoice f = charge.getInvoice();
			
			mp._getCharges().remove(charge);
			f._getCharges().remove(charge);
			charge._setPaymentMean(null);
			charge._setInvoice(null);
		}

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
		public static void link(Intervention intervention, Substitution substitution, SparePart sparePart) {
			substitution._setIntervention(intervention);
			substitution._setSparePart(sparePart);
			substitution.getIntervention()._getSustitutions().add(substitution);
			substitution.getSparePart()._getSustitutions().add(substitution);
		}
		
		public static void unlink(Substitution substitution) {
			substitution.getIntervention()._getSustitutions().remove(substitution);
			substitution.getSparePart()._getSustitutions().remove(substitution);
			substitution._setIntervention(null);
			substitution._setSparePart(null);
		}
	}

}
