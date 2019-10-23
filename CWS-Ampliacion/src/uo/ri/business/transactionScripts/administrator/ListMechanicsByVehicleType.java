package uo.ri.business.transactionScripts.administrator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CertificateDto;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.conf.Factory;
import uo.ri.persistance.certificate.CertificateGateway;
import uo.ri.persistance.vehicle.VehicleTypeGateway;

public class ListMechanicsByVehicleType {
	
	public List<CertificateDto> execute(){
		try (Connection c = Jdbc.getConnection()) {
			CertificateGateway gateway = Factory.persistance.getCertificateGateway();
			gateway.setConnection(c);
			return gateway.findAll();
		} catch (SQLException e) {
			throw new RuntimeException("findAllVehicleTypes");
		}
	}
	
	public List<VehicleTypeDto> findAllVehicleTypes() {
		try (Connection c = Jdbc.getConnection()) {
			VehicleTypeGateway gateway = Factory.persistance.getVehicleTypeGateway();
			gateway.setConnection(c);
			return gateway.findAll();
		} catch (SQLException e) {
			throw new RuntimeException("findAllVehicleTypes");
		}
	}
	
	public List<CertificateDto> findCertificateByVehicleTypeID(Long vehicletype_id) {
		try (Connection c = Jdbc.getConnection()) {
			CertificateGateway gateway = Factory.persistance.getCertificateGateway();
			gateway.setConnection(c);
			return gateway.findCertificatesByVehicleID(vehicletype_id);
		} catch (SQLException e) {
			throw new RuntimeException("findAllVehicleTypes");
		}
	}
}
