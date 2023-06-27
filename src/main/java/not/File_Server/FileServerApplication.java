package not.File_Server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileServerApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(FileServerApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
