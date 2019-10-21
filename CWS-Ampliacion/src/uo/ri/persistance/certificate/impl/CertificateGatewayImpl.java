package uo.ri.persistance.certificate.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uo.ri.business.dto.CertificateDto;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.dto.VehicleTypeDto;
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

	@Override
	public CertificateDto findCertificateByMechanic(Long mechanicId, Long vehicletype_Id) {
		CertificateDto dto = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL_FIND_CERTIFICATE_BY_MECHANIC = Conf.getInstance().getProperty("SQL_FIND_CERTIFICATE_BY_MECHANIC");
		try {
			pst = c.prepareStatement(SQL_FIND_CERTIFICATE_BY_MECHANIC);
			pst.setLong(1, mechanicId);
			pst.setLong(2, vehicletype_Id);
			rs = pst.executeQuery();
			while(rs.next()) {
				dto = new CertificateDto();
				dto.id = rs.getLong(1);
				dto.obtainedAt = rs.getDate(2);
				dto.mechanic = new MechanicDto();
				dto.mechanic.id = rs.getLong(3);
				dto.vehicleType = new VehicleTypeDto();
				dto.vehicleType.id = rs.getLong(4);
			}
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
		return dto;
	}

	@Override
	public void generateCertificate(Long mechanicID, Long vehicleTypeID) {
		PreparedStatement pst = null;
		String SQL = Conf.getInstance().getProperty("SQL_ADD_CERTIFICATE");
		try {
			pst = c.prepareStatement(SQL);
			pst.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			pst.setLong(2, mechanicID);
			pst.setLong(3, vehicleTypeID);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public List<CertificateDto> findCertificatesByVehicleID(Long vehicleID) {
		List<CertificateDto> certificateDtos = new ArrayList<CertificateDto>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL_FIND_CERTIFICATE_BY_VEHICLETYPE_ID = Conf.getInstance().getProperty("SQL_FIND_CERTIFICATE_BY_VEHICLETYPE_ID");
		try {
			pst = c.prepareStatement(SQL_FIND_CERTIFICATE_BY_VEHICLETYPE_ID);
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
