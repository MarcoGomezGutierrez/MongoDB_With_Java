package neo4j.Function;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.exceptions.AuthenticationException;

public class Conexion implements AutoCloseable {
	private static Driver driver;
	private static String usuario;

	public Conexion(String uri, String user, String password) throws AuthenticationException {
		usuario = user;
		driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
	}

	public static Driver getDriver() {
		return driver;
	}

	public void close() throws Exception {
		driver.close();
	}
	
	public static String getUsuario() {
		return usuario;
	}
}
