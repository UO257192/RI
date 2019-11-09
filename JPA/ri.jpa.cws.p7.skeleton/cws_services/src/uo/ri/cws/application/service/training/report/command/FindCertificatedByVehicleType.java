package uo.ri.cws.application.service.training.report.command;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CertificateRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CertificateDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;

import java.util.List;

public class FindCertificatedByVehicleType implements Command<List<CertificateDto>> {

    private CertificateRepository certificateRepository = Factory.repository.forCertificate();

    @Override
    public List<CertificateDto> execute() throws BusinessException {
        return DtoAssembler.toCertificateDtoList(certificateRepository.findCertificatedByVehicleType());
    }
}
