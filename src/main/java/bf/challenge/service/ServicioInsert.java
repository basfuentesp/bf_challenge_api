package bf.challenge.service;

import java.io.IOException;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ServicioInsert {
	private static Logger logs = LoggerFactory.getLogger(ServicioSelect.class);
	@Autowired
	private ServicioJDBC jdbc;
	
	public void begin(MultipartFile file, String table) throws SQLException, IOException {
		try {
			jdbc.doInsert(file, table);
		} catch (Exception e) {
			logs.info(e.getMessage());
		}
	}
}