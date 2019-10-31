package uo.ri.cws.infrastructure.persistence.jpa.repository;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class MechanicJpaRepository
        extends BaseJpaRepository<Mechanic>
        implements MechanicRepository {

    @Override
    public Optional<Mechanic> findByDni(String dni) {
        return Jpa.getManager().createNamedQuery("Mechanic.findByDni", Mechanic.class).setParameter(1, dni).getResultStream().findFirst();
    }

}
