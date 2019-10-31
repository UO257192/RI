package uo.ri.conf;

import uo.ri.persistance.MechanicGateway;
import uo.ri.persistance.impl.MechanicGatewayImpl;

public class PersistanceFactory {
	public static MechanicGateway getMechanicCrudService() {
		return new MechanicGatewayImpl();
	}

}
