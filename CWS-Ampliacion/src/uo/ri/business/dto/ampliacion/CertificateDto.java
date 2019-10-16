package uo.ri.business.dto.ampliacion;

import java.util.Date;

import uo.ri.business.dto.MechanicDto;

public class CertificateDto {

	public Long id;

	public MechanicDto mechanic;
	public VehicleTypeDto vehicleType;
	public Date obtainedAt;

}
