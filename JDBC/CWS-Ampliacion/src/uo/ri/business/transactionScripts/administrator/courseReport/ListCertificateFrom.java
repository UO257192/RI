package uo.ri.business.transactionScripts.administrator.courseReport;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CertificateDto;
import uo.ri.conf.Factory;
import uo.ri.persistance.certificate.CertificateGateway;

public class ListCertificateFrom {
	
	private Date date;

	public ListCertificateFrom(Date date) {
		this.date = date;
	}
	
	public List<CertificateDto> execute(){
		try (Connection c = Jdbc.getConnection()) {
			CertificateGateway gateway = Factory.persistance.getCertificateGateway();
			gateway.setConnection(c);
			return gateway.findCertificatesFromDate(date);
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}

}
