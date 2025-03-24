package bf.challenge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import bf.challenge.service.ServicioSelect;
import bf.challenge.service.ServicioInsert;

@RestController
@RequestMapping("/api")
public class ControladorMain {
	private static Logger logs = LoggerFactory.getLogger(ControladorMain.class);
	
    @Autowired
    private ServicioSelect sqlSelect;
    
    @Autowired
    private ServicioInsert sqlInsert;
    
	@PostMapping("/insert")
	public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file, @RequestParam("table") String table) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Por favor sube un archivo CSV y con header.");
        }
        logs.info("Archivo recibido: " + file.getOriginalFilename());
 
        try {
        	sqlInsert.begin(file, table);
			return new ResponseEntity<>(HttpStatus.OK.name(),HttpStatus.OK);
		} catch (Exception e) {
			logs.info(e.getMessage());
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
    }
	
	 @PostMapping("/select")
	    public ResponseEntity<String> exampleQuery(){
	    	try {
	    		logs.info("Se ejecuta query de ejemplo hacia la BD Local MySQL");
	    		sqlSelect.begin();
				return new ResponseEntity<>(HttpStatus.OK.name(),HttpStatus.OK);
			} catch (Exception e) {
				logs.info(e.getMessage());
				return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
			}
	    }
}