package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

	public String dataAtual = "";
	
	public String data() {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		dataAtual = dtf.format(LocalDateTime.now());
		
		return dataAtual;
	}
	
}
