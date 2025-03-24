package bf.challenge.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import bf.challenge.model.Jobs;

@Service
public class ServicioJDBC {
	private static Logger logs = LoggerFactory.getLogger(ServicioSelect.class);
	
	Connection connection = null;
	private Statement stm = null;
	private ResultSet rs = null;
	
	String host = "localhost";
	//String host = "host.docker.internal";
	String port = "3306";
	String user = "root";
	String password = System.getenv("PASSBD");
	String schema = "challenge";
	String query = "select job_id, job from challenge.Jobs";
	String fetchsize = "100000";
	
	
	public ServicioJDBC(){
		
	}
	
	public String doURLCon(String host, String port, String database) {
		return "jdbc:mysql://"+host+":"+port+"/"+database;
	}
	
	private Connection doConnect(String host, String port, String user, String schema, String password) throws SQLException {
		return DriverManager.getConnection(doURLCon(host, port, schema), user, password);
	}
	
	public void doQuery() throws IOException, SQLException {
		logs.info("Se inicia sesion con BD MySQL");
		Connection con = doConnect(host,port,user,schema,password);
		
		try {
			con.setAutoCommit(false);
			stm = con.createStatement();
			stm.setFetchSize(Integer.parseInt(fetchsize));
			
			rs = stm.executeQuery(query);

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
	}
	
	public ResponseEntity<String> doInsert(MultipartFile file, String table) throws IOException, SQLException, CsvValidationException {
		logs.info("Se inicia sesion con BD MySQL");
		Connection con = doConnect(host,port,user,schema,password);
        CSVReader reader = null;

        try {           
            // Leer el archivo CSV
            reader = new CSVReader(new InputStreamReader(file.getInputStream()));
            String [] nextLine;            
            String [] header = reader.readNext();  // Lee la primera fila para obtener las cabeceras
            
            if (header == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El archivo CSV no tiene cabecera.");
            }
            
            int numeroColumnas = header.length;
            
            StringBuilder insertQuery = new StringBuilder("INSERT INTO "+table+" (");
            
            if (table == "challenge.Jobs") {
            	Jobs jobs = new Jobs();
            	jobs.getJobId();
            	            	
            }
           
            // Generar columnas para la consulta INSERT INTO dinamico
            for (int i = 0; i < numeroColumnas; i++) {
                insertQuery.append(header[i]);
                if (i < numeroColumnas - 1) {
                    insertQuery.append(",");
                }
            }
            
            insertQuery.append(") VALUES (");

            // Generar los valores para las columnas que vienen desde el CSV con placeholder pregunta ?
            for (int i = 0; i < numeroColumnas; i++) {
                insertQuery.append("?");
                if (i < numeroColumnas - 1) {
                    insertQuery.append(", ");
                }
            }
            
            insertQuery.append(")");

            // Preparar la consulta SQL hacia el motor MySQL
            
            PreparedStatement preparedStatement = con.prepareStatement(insertQuery.toString());
            
            // Leer el resto de las filas del CSV para insertar
            
            while ((nextLine = reader.readNext()) != null) {
                for (int i = 0; i < numeroColumnas; i++) {
                    preparedStatement.setString(i + 1, nextLine[i]);  // Asignamos cada valor de la fila valor por valor
                }

                // Se ejecuta el insert con executeUpdate ya buildeado
                preparedStatement.executeUpdate();
            }
            
            logs.info("Datos del archivo CSV insertados correctamente.");
            return ResponseEntity.status(HttpStatus.OK).body("Datos del archivo CSV insertados correctamente.");
            
        } catch (SQLException | IOException | CsvValidationException e) {
        	logs.info("Ha ocurrido un error!");
        	logs.error(e.getMessage());
        } finally {
            if (reader != null) {
            	reader.close();
            } 
            if (con != null) {
            	con.close();
            }
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hubo un error al procesar el archivo CSV.");
	}
}