package uo.ri.cws.domain;

/**
 * Associations classes
 *
 * @author UO257192
 */
public class Associations {

    /**
     * Associations class for enroll relation
     *
     * @author UO257192
     */
    public static class Enroll {

        /**
         * Links mechanic, enrollment and course
         *
         * @param mechanic   Mechanic
         * @param enrollment Enrollment
         * @param course     Course
         */
        public static void link(Mechanic mechanic, Enrollment enrollment, Course course) {
            enrollment._setCourse(course);
            enrollment._setMechanic(mechanic);

            mechanic._getEnrollments().add(enrollment);
            course._getEnrollments().add(enrollment);
        }
    }

    /**
     * Associations class for dedicate relation
     *
     * @author UO257192
     */
    public static class Dedicate {

        /**
         * Links course, dedication and vehicleType
         *
         * @param course      Course
         * @param dedication  Dedication
         * @param vehicleType VehicleType
         */
        public static void link(Course course, Dedication dedication, VehicleType vehicleType) {
            dedication._setCourse(course);
            dedication._setVehicleType(vehicleType);

            vehicleType._getDedications().add(dedication);
            course._getDedications().add(dedication);
        }

        /**
         * Unlinks course, dedication and vehicleType
         *
         * @param course      Course
         * @param dedication  Dedication
         * @param vehicleType VehicleType
         */
        public static void unlink(Course course, Dedication dedication, VehicleType vehicleType) {
            vehicleType._getDedications().remove(dedication);
            course._getDedications().remove(dedication);

            dedication._setCourse(null);
            dedication._setVehicleType(null);
        }
    }


    /**
     * Associations class for certify relation
     *
     * @author UO257192
     */
    public static class Certify {

        /**
         * Links mechanic, certificate and vehicleType
         *
         * @param mechanic    Mechanic
         * @param certificate Certificate
         * @param vehicleType VehicleType
         */
        public static void link(Mechanic mechanic, uo.ri.cws.domain.Certificate certificate, VehicleType vehicleType) {
            certificate._setMechanic(mechanic);
            certificate._setVehicleType(vehicleType);

            mechanic._getCertificates().add(certificate);
            vehicleType._getCertificates().add(certificate);
        }

        /**
         * Unlinks mechanic, certificate and vehicleType
         *
         * @param mechanic    Mechanic
         * @param certificate Certificate
         */
        public static void unlink(Mechanic mechanic, uo.ri.cws.domain.Certificate certificate) {
            mechanic._getCertificates().remove(certificate);
            certificate._setMechanic(null);
        }
    }


    /**
     * Associations class for own relation
     *
     * @author UO257192
     */
    public static class Own {

        /**
         * Links client and vehicle
         *
         * @param client  Client
         * @param vehicle Vehicle
         */
        public static void link(Client client, Vehicle vehicle) {
            vehicle._setClient(client);
            client._getVehicles().add(vehicle);
        }

        /**
         * Unlinks client and vehicle
         *
         * @param client  Client
         * @param vehicle Vehicle
         */
        public static void unlink(Client client, Vehicle vehicle) {
            client._getVehicles().remove(vehicle);
            vehicle._setClient(null);
        }
    }

    /**
     * Associations class for classify relation
     *
     * @author UO257192
     */
    public static class Classify {

        /**
         * Links vehicleType and vehicle
         *
         * @param vehicleType VehicleType
         * @param vehicle     Vehicle
         */
        public static void link(VehicleType vehicleType, Vehicle vehicle) {
            vehicle._setVehicleType(vehicleType);
            vehicleType._getVehicles().add(vehicle);
        }

        /**
         * Unlinks vehicleType and vehicle
         *
         * @param vehicleType VehicleType
         * @param vehicle     Vehicle
         */
        public static void unlink(VehicleType vehicleType, Vehicle vehicle) {
            vehicleType._getVehicles().remove(vehicle);
            vehicle._setVehicleType(null);
        }
    }

    /**
     * Associations class for pay relation
     *
     * @author UO257192
     */
    public static class Pay {

        /**
         * Link paymentmean and client
         *
         * @param paymentMean PaymentMean
         * @param client      Client
         */
        public static void link(PaymentMean paymentMean, Client client) {
            paymentMean._setClient(client);
            client._getPaymentMeans().add(paymentMean);
        }

        /**
         * Unlink paymentmean and client
         *
         * @param paymentMean PaymentMean
         * @param client      Client
         */
        public static void unlink(Client client, PaymentMean paymentMean) {
            client._getPaymentMeans().remove(paymentMean);
            paymentMean._setClient(null);
        }
    }

    /**
     * Associations class for order relation
     *
     * @author UO257192
     */
    public static class Order {

        /**
         * Links vehicle and WorkOrder
         *
         * @param vehicle   Vehicle
         * @param workOrder Workorder
         */
        public static void link(Vehicle vehicle, WorkOrder workOrder) {
            workOrder._setVehicle(vehicle);
            vehicle._getWorkOrders().add(workOrder);
        }

        /**
         * Unlinks Vehicle and WorkOrder
         *
         * @param vehicle   Vehicle
         * @param workOrder WorkOrder
         */
        public static void unlink(Vehicle vehicle, WorkOrder workOrder) {
            vehicle._getWorkOrders().remove(workOrder);
            workOrder._setVehicle(null);
        }
    }

    /**
     * Associations class for to invoice relation
     *
     * @author UO257192
     */
    public static class ToInvoice {

        /**
         * Links WorkOrder and Invoice
         *
         * @param workOrder WorkOrder
         * @param invoice   Invoice
         */
        public static void link(WorkOrder workOrder, Invoice invoice) {
            workOrder._setInvoice(invoice);
            invoice._getWorkOrders().add(workOrder);
        }

        /**
         * Unlinks WorkOrder and Invoice
         *
         * @param workOrder WorkOrder
         * @param invoice   Invoice
         */
        public static void unlink(WorkOrder workOrder, Invoice invoice) {
            invoice._getWorkOrders().remove(workOrder);
            workOrder._setInvoice(null);
        }
    }

    /**
     * Associations class for charges relation
     *
     * @author UO257192
     */
    public static class Charges {

        /**
         * Links PaymentMean, Charge and Invoice
         *
         * @param paymentMean PaymentMean
         * @param charge      Charge
         * @param invoice     Invoice
         */
        public static void link(PaymentMean paymentMean, Charge charge, Invoice invoice) {
            charge._setPaymentMean(paymentMean);
            charge._setInvoice(invoice);
            paymentMean._getCharges().add(charge);
            invoice._getCharges().add(charge);
        }

        /**
         * Unlink Charge
         *
         * @param charge Charge
         */
        public static void unlink(Charge charge) {
            charge.getPaymentMean()._getCharges().remove(charge);
            charge.getInvoice()._getCharges().remove(charge);
            charge._setPaymentMean(null);
            charge._setInvoice(null);
        }
    }

    /**
     * Associations class for assign relation
     *
     * @author UO257192
     */
    public static class Assign {

		/**
		 * Links Mechanic and WorkOrder
		 * @param mechanic Mechanic
		 * @param workOrder WorkOrder
		 */
        public static void link(Mechanic mechanic, WorkOrder workOrder) {
            workOrder._setMechanic(mechanic);
            mechanic._getAssigned().add(workOrder);
        }

		/**
		 * Unlinks Mechanic and WorkOrder
		 * @param mechanic Mechanic
		 * @param workOrder WorkOrder
		 */
		public static void unlink(Mechanic mechanic, WorkOrder workOrder) {
            mechanic._getAssigned().remove(workOrder);
            workOrder._setMechanic(null);
        }

    }

    /**
     * Associations class interneve relation
     *
     * @author UO257192
     */
    public static class Intervene {

		/**
		 * Link WorkOrder, Intervention and Mechanic
		 * @param workOrder WorkOrder
		 * @param intervention Intervention
		 * @param mechanic Mechanic
		 */
        public static void link(WorkOrder workOrder, Intervention intervention, Mechanic mechanic) {
            intervention._setWorkOrder(workOrder);
            intervention._setMechanic(mechanic);
            workOrder._getInterventions().add(intervention);
            mechanic._getInterventions().add(intervention);
        }

		/**
		 * Unlink Intervention
		 * @param intervention Intervention
		 */
		public static void unlink(Intervention intervention) {
            intervention.getWorkOrder()._getInterventions().remove(intervention);
            intervention.getMechanic()._getInterventions().remove(intervention);
            intervention._setWorkOrder(null);
            intervention._setMechanic(null);
        }
    }

    /**
     * Associations class for sustitute relation
     *
     * @author UO257192
     */
    public static class Sustitute {

		/**
		 * Links Intervention, Substitution and SparePart
		 * @param intervention Intervention
		 * @param substitution Substitution
		 * @param sparePart SparePart
		 */
        public static void link(Intervention intervention, Substitution substitution, SparePart sparePart) {
            substitution._setIntervention(intervention);
            substitution._setSparePart(sparePart);
            intervention._getSubstitutions().add(substitution);
            sparePart._getSubstitutions().add(substitution);
        }

		/**
		 * Unlink Substitution
		 * @param sustitution Substitution
		 */
		public static void unlink(Substitution sustitution) {
            sustitution.getIntervention()._getSubstitutions().remove(sustitution);
            sustitution.getSparePart()._getSubstitutions().remove(sustitution);
            sustitution._setIntervention(null);
            sustitution._setSparePart(null);
        }
    }

}
