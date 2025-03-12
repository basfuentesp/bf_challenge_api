package bf.challenge.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

@Service
public class ServicioJDBC {
	private static Logger logs = LoggerFactory.getLogger(ServicioInicio.class);
		
	public boolean doQuery() throws IOException, SQLException {
		String host = "localhost";
		String port = "3306";
		String user = "root";
		String password = "null";
		
		String table = "challenge.Jobs";
		String query = "insert into";
		String fetchsize = "100000";
		
		logs.info("Se inicia sesion con BD MySQL");
		
		Connection connection = doConnect(host,port,user,password);
		
		//Tablas
		
		return false;
	}
	
	
	private Connection doConnect(String host, String port, String user, String password) {
		return null;
	}
	
}