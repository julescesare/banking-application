package Models;

import java.sql.Connection;

public abstract class AbstractConnectionDAO {
	
	protected Connection connection = SingleConnection.getConnectiont();
	

}
