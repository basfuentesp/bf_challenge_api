package bf.challenge.controller;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api")
public class ControladorMain {
	private static Logger myLogger = LoggerFactory.getLogger(ControladorMain.class);

	@PostMapping("/upload")
	public List<String[]> uploadFile(@RequestParam("file") MultipartFile file) {
		List<String[]> records = new ArrayList<>();

		if (file.isEmpty()) {
			// return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
			myLogger.error("error, archivo vacio");
		}

		String fileName = file.getOriginalFilename();
		myLogger.info("El archivo a procesar es: ", fileName);

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
}
