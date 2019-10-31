package uo.ri.business.impl.administrator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.MechanicDto;

public class UpdateMechanic {
	private static String SQL = "update TMechanics " + "set name = ?, surname = ? " + "where id = ?";
	private MechanicDto mechanicDto;

	public UpdateMechanic(MechanicDto mechanicDto) {
		super();
		this.mechanicDto = mechanicDto;
	}

	public void execute() {
		// Process
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(SQL);
			pst.setString(1, mechanicDto.name);
			pst.setString(2, mechanicDto.surname);
			pst.setLong(3, mechanicDto.id);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst, c);
		}
	}

}
