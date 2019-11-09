package uo.ri.cws.application.service.workorder.assign.command;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CertificateRepository;
import uo.ri.cws.application.repository.VehicleTypeRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CertificateDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.VehicleType;

import java.util.List;
import java.util.Optional;

public class FindCertificatesByVehicleTypeId implements Command<List<CertificateDto>> {

    private String id;
    private CertificateRepository certificateRepository = Factory.repository.forCertificate();
    private VehicleTypeRepository vehicleTypeRepository = Factory.repository.forVehicleType();

    public FindCertificatesByVehicleTypeId(String id) {
        this.id = id;
    }

    @Override
    public List<CertificateDto> execute() throws BusinessException {
        BusinessCheck.isNotNull(id, "El id del tipo de vehiculo es null");
        BusinessCheck.isNotEmpty(id, "El id del tipo de vehiculo está vacio");
        Optional<VehicleType> vehicleType = vehicleTypeRepository.findById(id);
        BusinessCheck.isTrue(vehicleType.isPresent(), "No existe el tipo de vehiculo");
        VehicleType type = vehicleType.get();
        return DtoAssembler.toCertificateDtoList(certificateRepository.findMechanicsCertificatedForVehicleType(type));
    }
}
