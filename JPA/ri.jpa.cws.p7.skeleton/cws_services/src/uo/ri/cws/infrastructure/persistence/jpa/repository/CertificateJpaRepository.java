package uo.ri.cws.infrastructure.persistence.jpa.repository;

import uo.ri.cws.application.repository.CertificateRepository;
import uo.ri.cws.domain.Certificate;
import uo.ri.cws.domain.Intervention;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

import java.util.List;
import java.util.Optional;

public class CertificateJpaRepository
        extends BaseJpaRepository<Certificate>
        implements CertificateRepository {
    @Override
    public Optional<Certificate> findCertificateFor(Mechanic mechanic, VehicleType vehicleType) {
        return Jpa.getManager().createNamedQuery("Certificate.findForMechanicVehicleType", Certificate.class)
                .setParameter(1, mechanic)
                .setParameter(2, vehicleType)
                .getResultStream().findFirst();
    }

    @Override
    public List<Certificate> findCertificatedByVehicleType() {
        return Jpa.getManager()
                .createNamedQuery("Certificate.findCertificatedByVehicleType", Certificate.class)
                .getResultList();
    }
}
