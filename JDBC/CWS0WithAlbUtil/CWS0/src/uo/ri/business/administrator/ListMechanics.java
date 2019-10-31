package uo.ri.business.administrator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.MechanicDto;

public class ListMechanics {
	private static String SQL = "select id, dni, name, surname from TMechanics";
	
	public List<MechanicDto> execute(){
		List<MechanicDto> mechanicDtos = null;
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(SQL);
			
			rs = pst.executeQuery();
			mechanicDtos = new ArrayList<MechanicDto>();
			while(rs.next()) {
				MechanicDto mechanicDto = new MechanicDto();
				mechanicDto.id = rs.getLong(1);
				mechanicDto.dni = rs.getString(2);
				mechanicDto.name = rs.getString(3);
				mechanicDto.surname = rs.getString(4);
				mechanicDtos.add(mechanicDto);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return mechanicDtos;
		
	}
}
