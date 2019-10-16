package uo.ri.persistance.certificate.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uo.ri.business.dto.MechanicDto;
import uo.ri.business.dto.ampliacion.CertificateDto;
import uo.ri.business.dto.ampliacion.VehicleTypeDto;
import uo.ri.conf.Conf;
import uo.ri.persistance.certificate.CertificateGateway;

public class CertificateGatewayImpl implements CertificateGateway {

	private Connection c;

	@Override
	public void setConnection(Connection c) {
		this.c = c;
	}

	@Override
	public List<CertificateDto> findAll() {
		List<CertificateDto> certificateDtos = new ArrayList<CertificateDto>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL_FIND_ALL_CERTIFICATES = Conf.getInstance().getProperty("SQL_FIND_ALL_CERTIFICATES");
		try {
			pst = c.prepareStatement(SQL_FIND_ALL_CERTIFICATES);
			rs = pst.executeQuery();
			while(rs.next()) {
				CertificateDto dto = new CertificateDto();
				dto.id = rs.getLong(1);
				dto.obtainedAt = rs.getDate(2);
				dto.mechanic = new MechanicDto();
				dto.mechanic.id = rs.getLong(3);
				dto.vehicleType = new VehicleTypeDto();
				dto.vehicleType.id = rs.getLong(4);
				certificateDtos.add(dto);
			}
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
		return certificateDtos;
	}

}
