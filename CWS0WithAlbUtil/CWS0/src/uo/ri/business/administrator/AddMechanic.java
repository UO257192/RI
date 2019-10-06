package uo.ri.business.administrator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.MechanicDto;
import uo.ri.common.BusinessException;

public class AddMechanic {

	private static String SQL = "insert into TMechanics(dni, name, surname) values (?, ?, ?)";
	private MechanicDto mechanicDto;

	public AddMechanic(MechanicDto mechanicDto) {
		super();
		this.mechanicDto = mechanicDto;
	}

	public void execute() throws BusinessException {
		// Process
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(SQL);
			pst.setString(1, mechanicDto.dni);
			pst.setString(2, mechanicDto.name);
			pst.setString(3, mechanicDto.surname);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst, c);
		}

	}

}
