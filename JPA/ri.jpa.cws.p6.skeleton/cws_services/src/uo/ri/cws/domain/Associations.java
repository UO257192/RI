package uo.ri.cws.domain;

public class Associations {

	public static class Dedicate {

		public static void link(Dedication dedication) {
			dedication.getCourse()._getDedications().add(dedication);
			dedication.getVehicleType()._getDedications().add(dedication);
		}

		public static void unlink(Course course) {
			for (Dedication dedication : course._getDedications()) {
				dedication.getVehicleType()._getDedications().remove(dedication);
				dedication._setVehicleType(null);
				dedication._setCourse(null);
			}
			course._getDedications().clear();
		}
	}

	public static class Certify {


		public static void link(Mechanic mechanic, uo.ri.cws.domain.Certificate certificate) {
			certificate._setMechanic(mechanic);
			mechanic._getCertificates().add(certificate);
		}

		public static void unlink(Mechanic mechanic, uo.ri.cws.domain.Certificate certificate) {
			mechanic._getCertificates().remove(certificate);
			certificate._setMechanic(null);
		}
	}

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
			vehicle._setVehicleType(vehicleType);
			vehicleType._getVehicles().add(vehicle);
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

        public static void unlink(WorkOrder workOrder, Invoice invoice) {
			invoice._getWorkOrders().remove(workOrder);
			workOrder._setInvoice(null);
        }

		public static void link(WorkOrder workOrder, Invoice invoice) {
			workOrder._setInvoice(invoice);
			invoice._getWorkOrders().add(workOrder);
		}
	}

	public static class Charges {

		public static void link(PaymentMean paymentMean, Charge charge, Invoice invoice) {
			charge._setPaymentMean(paymentMean);
			charge._setInvoice(invoice);
			paymentMean._getCharges().add(charge);
			invoice._getCharges().add(charge);
		}

		public static void unlink(Charge charge) {
			charge.getPaymentMean()._getCharges().remove(charge);
			charge.getInvoice()._getCharges().remove(charge);
			charge._setPaymentMean(null);
			charge._setInvoice(null);
		}
	}

	public static class Assign {

		public static void unlink(Mechanic mechanic, WorkOrder workOrder) {
			mechanic._getAssigned().remove(workOrder);
			workOrder._setMechanic(null);
		}

		public static void link(Mechanic mechanic, WorkOrder workOrder) {
			workOrder._setMechanic(mechanic);
			mechanic._getAssigned().add(workOrder);
		}
	}

	public static class Intervene {

		public static void link(WorkOrder workOrder, Intervention intervention, Mechanic mechanic) {
			intervention._setWorkOrder(workOrder);
			intervention._setMechanic(mechanic);
			workOrder._getInterventions().add(intervention);
			mechanic._getInterventions().add(intervention);
		}

		public static void unlink(Intervention intervention) {
			intervention.getWorkOrder()._getInterventions().remove(intervention);
			intervention.getMechanic()._getInterventions().remove(intervention);
			intervention._setWorkOrder(null);
			intervention._setMechanic(null);
		}
	}

	public static class Sustitute {

		public static void link(Intervention intervention, Substitution substitution, SparePart sparePart) {
			substitution._setIntervention(intervention);
			substitution._setSparePart(sparePart);
			intervention._getSubstitutions().add(substitution);
			sparePart._getSubstitutions().add(substitution);
		}

		public static void unlink(Substitution sustitution) {
			sustitution.getIntervention()._getSubstitutions().remove(sustitution);
			sustitution.getSparePart()._getSubstitutions().remove(sustitution);
			sustitution._setIntervention(null);
			sustitution._setSparePart(null);
		}
	}

}
