package bf.challenge.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	private Statement stm = null;
	private ResultSet rs = null;
	private boolean outputs = false;
	
	public ServicioJDBC(){
		
	}
	
	public String doURLCon(String host, String port, String database) {
		return "jdbc:mysql://"+host+":"+port+"/"+database;
	}
	
	private Connection doConnect(String host, String port, String user, String password) throws SQLException {
		return DriverManager.getConnection(doURLCon(host, port, password), user, password);
	}
	
	public boolean doQuery() throws IOException, SQLException {
		String host = "localhost";
		String port = "3306";
		String user = "root";
		String password = "null";
		
		String table = "challenge.Jobs";
		String query = "insert into ";
		String fetchsize = "100000";
		
		logs.info("Se inicia sesion con BD MySQL");
		Connection con = doConnect(host,port,user,password);
		
		try {
			con.setAutoCommit(false);
			
			stm = con.createStatement();
			
			stm.setFetchSize(Integer.parseInt(fetchsize));
			
			rs = stm.executeQuery(query);
			
			outputs = inserts.saveInsert(rs, table, ".csv");
			
		} catch (SQLException e) {
			logs.info(e.getMessage());
		} finally {
			if (rs != null) {
				rs.close();
				stm.close();
			}
		}
		return outputs;
	}
	
	
	
}