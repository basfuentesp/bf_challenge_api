package bf.challenge.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.opencsv.exceptions.CsvValidationException;

import bf.challenge.service.ServicioInicio;
import bf.challenge.service.ServicioJDBC;

@RestController
@RequestMapping("/api")
public class ControladorMain {
	private static Logger logs = LoggerFactory.getLogger(ControladorMain.class);
	
    @Autowired
    private ServicioInicio iniciarApp;
    
    @PostMapping("/iniciar")
    public ResponseEntity<String> extraerCsv(@RequestBody String jsonFile){
    	try {
			iniciarApp.iniciarAplicacion();
			return new ResponseEntity<>(HttpStatus.OK.name(),HttpStatus.OK);
		} catch (Exception e) {
			logs.info(e.getMessage());
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
    }
	
    
    
    
	@PostMapping("/upload")
	public List<String[]> uploadFile(@RequestParam("file") MultipartFile file) {
		List<String[]> records = new ArrayList<>();

		if (file.isEmpty()) {
			// return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
			logs.error("error, archivo vacio");
		}

		String fileName = file.getOriginalFilename();
		logs.info("El archivo a procesar es: ", fileName);

		try (Reader reader = new InputStreamReader(file.getInputStream())) {
			Iterable<CSVRecord> csvRecords = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);

			for (CSVRecord csvRecord : csvRecords) {
				String[] row = new String[csvRecord.size()];
				for (int i = 0; i < csvRecord.size(); i++) {
					row[i] = csvRecord.get(i);
				}
				records.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return records;
	}
	
	@PostMapping("/insert")
	public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) throws IOException, CsvValidationException, NumberFormatException, DataAccessException {
        // Verifica que el archivo no esté vacío
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Por favor, sube un archivo CSV.");
        }
        
        System.out.println("Archivo recibido: " + file.getOriginalFilename());
        
        // Llama a un servicio para procesar el archivo
        //servicioJdbc.processCsvFile(file);
        return ResponseEntity.ok("Archivo CSV procesado e insertado con éxito.");
    }
	
	
}