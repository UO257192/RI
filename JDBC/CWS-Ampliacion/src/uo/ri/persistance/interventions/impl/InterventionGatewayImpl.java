package uo.ri.persistance.interventions.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import uo.ri.conf.Conf;
import uo.ri.persistance.interventions.InterventionGateway;

public class InterventionGatewayImpl implements InterventionGateway {
	private Connection c;

	@Override
	public void setConnection(Connection c) {
		this.c = c;
	}

	@Override
	public boolean hasInterventions(Long workorderID) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL_FIND_INTERVENTIONS_BY_WORKORDER = Conf.getInstance().getProperty("SQL_FIND_INTERVENTIONS_BY_WORKORDER");
		try {
			pst = c.prepareStatement(SQL_FIND_INTERVENTIONS_BY_WORKORDER);
			pst.setLong(1, workorderID);
			rs = pst.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	

}
