package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {


	// Constantes de instancia de la clase
	/* Se declaran constantes, porque van a ser siempre los mismos valores
	cada vez que se vaya a crear un objeto de tipo DbConnection
	*/
	static String bd = "hlc";
	static String login = "root";
	static String password = "";
	static String url = "jdbc:mysql://localhost/"+bd;
	// static String password = "SFQatt64612";
	// static String url = "jdbc:mysql://node11336-desarrollowebcdpjosecabrera.jelastic.cloudhosted.es/sistemadb";
	// Esta variable va a guardar la conexion
	Connection conn = null;
	/**
	* Constructor de la clase. Se llama constructor porque tiene el mismo
	* nombre que la clase y cuando se crea un nuevo objeto de esta clase es como
	* se va a inicializar al crear un nuevo objeto de este tipo.
	 * @return 
	*
	*/
	
	public DbConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");		//obtenemos el driver para mysql
			conn = (Connection) DriverManager.getConnection(url, login, password); //obtenemos una conexion con los parametros especificados anteriormente

			if (conn != null) { 	// Si conn no es nulo, significa que pidimos conectarnos
				System.out.println("Connecting database [" + conn + "] OK");
			}
		} catch (SQLException e) { // Excepcion ocurrida por la conexion
			System.out.println("Excepcion conexion: " + e.getMessage());
		} catch (ClassNotFoundException e) { // Excepcion ocurrida por no encontrar el driver
			System.out.println("Excepcion driver: " + e.getMessage());
		}
	}//DbConnection
	
	public Connection getConnection() {
		return conn;
	}//getConnection
	
	public void disconnect() {
		System.out.println("Closing database: [" + conn + "] OK");
		if (conn != null) {
			try {
				// System.out.println("Desconectado de " + bd + " OK");
				conn.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
	}//disconnect
	
}
