package uo.ri.cws.application.repository;

import uo.ri.cws.domain.Certificate;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;

import java.util.List;
import java.util.Optional;

public interface CertificateRepository extends Repository<Certificate> {

	Optional<Certificate> findCertificateFor(Mechanic mechanic, VehicleType vehicleType);

	List<Certificate> findCertificatedByVehicleType();
}
