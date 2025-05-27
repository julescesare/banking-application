package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingleConnection {

	// les parametre de connection
	String db = "GestionCompteBancaire";
	String user = "root";
	String pwd = "root";
	String url = "jdbc:mysql://localhost:3306/" + db;
	private static Connection connection = null; // permet d'utiliser une seul instance de connection pour toute les
													// requetes

	private SingleConnection() {
		try {
			connection = DriverManager.getConnection(url, user, pwd);
			System.out.println("Connection effectuer avec succes");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public static Connection getConnectiont() {

		if (connection == null) {
			new SingleConnection();
		}
		return connection;

	}
}
