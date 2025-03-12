package bf.challenge.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioJDBC {
	private static Logger logs = LoggerFactory.getLogger(ServicioInicio.class);
	
	Connection connection = null;
	private Statement stm = null;
	private ResultSet rs = null;
	private boolean outputs = false;
	
	@Autowired
	private ServicioInsert inserts;
	
	public ServicioJDBC(){
		
	}
	
	public String doURLCon(String host, String port, String database) {
		return "jdbc:mysql://"+host+":"+port+"/"+database;
	}
	
	private Connection doConnect(String host, String port, String user, String schema, String password) throws SQLException {
		return DriverManager.getConnection(doURLCon(host, port, schema), user, password);
	}
	
	public void doQuery() throws IOException, SQLException {
		String host = "localhost";
		String port = "3306";
		String user = "root";
		String password = System.getenv("PASSBD");
		String schema = "challenge";
		String query = "select job_id, job from challenge.Jobs";
		String fetchsize = "100000";
		
		logs.info("Se inicia sesion con BD MySQL");
		Connection con = doConnect(host,port,user,schema,password);
		
		try {
			con.setAutoCommit(false);
	
			stm = con.createStatement();
			
			stm.setFetchSize(Integer.parseInt(fetchsize));
			
			rs = stm.executeQuery(query);
			
			//outputs = inserts.saveInsert(rs, table, ".csv");
			
			// Procesar los resultados
            while (rs.next()) {
                int job_id = rs.getInt("job_id");
                String job = rs.getString("job");
                logs.info("Resultado de Query:\n");
                logs.info("job_id: " + job_id + ", job: " + job);
            }
		} catch (SQLException e) {
			logs.info(e.getMessage());
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (rs != null) {
				stm.close();
			}
		}
		//return outputs;
	}
}