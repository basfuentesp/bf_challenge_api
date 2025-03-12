package bf.challenge.service;

import java.io.IOException;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioInicio {
	private static Logger logs = LoggerFactory.getLogger(ServicioInicio.class);
	@Autowired
	private ServicioJDBC jdbc;
	
	public void beginApp() throws SQLException, IOException {
		try {
			jdbc.doQuery();
		} catch (Exception e) {
			logs.info(e.getMessage());
		}
	}
}