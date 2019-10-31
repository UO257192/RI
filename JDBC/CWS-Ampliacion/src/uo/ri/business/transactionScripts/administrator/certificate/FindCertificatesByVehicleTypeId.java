package uo.ri.business.transactionScripts.administrator.certificate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CertificateDto;
import uo.ri.conf.Factory;
import uo.ri.persistance.certificate.CertificateGateway;

public class FindCertificatesByVehicleTypeId {
	
	private Long vehicleTypeID;
	
	public FindCertificatesByVehicleTypeId(Long typeID) {
		this.vehicleTypeID = typeID;
	}
	
	public List<CertificateDto> execute(){
		try (Connection c = Jdbc.getConnection()) {
			CertificateGateway gateway = Factory.persistance.getCertificateGateway();
			gateway.setConnection(c);
			return gateway.findCertificatesByVehicleID(vehicleTypeID);
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}

}
