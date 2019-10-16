package uo.ri.persistance.certificate;

import java.sql.Connection;
import java.util.List;

import uo.ri.business.dto.ampliacion.CertificateDto;

public interface CertificateGateway {
	
	void setConnection(Connection c);
	
	List<CertificateDto> findAll();
}
