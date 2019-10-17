package uo.ri.persistance.certificate;

import java.sql.Connection;
import java.util.List;

import uo.ri.business.dto.CertificateDto;

public interface CertificateGateway {
	
	void setConnection(Connection c);
	
	List<CertificateDto> findAll();
	
	CertificateDto findCertificateByMechanic(Long mechanicId, Long vehicletype_Id);
	
	void generateCertificate(Long mechanicID, Long vehicleTypeID);
	
	List<CertificateDto> findCertificatesByVehicleID(Long vehicleID);
}
