import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

public class ReadPropertyFile {
	FileInputStream fis;
	
	@Test
	public void readData() {
		File file= new File("prop\\config.properties");
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			System.out.println("File no available");
			e.printStackTrace();
		}
		
		Properties pro= new Properties();
		
		//load the property fiel using load method of Property class
		try {
			pro.load(fis);
		} catch (IOException e) {
			System.out.println("File not have any data");
			e.printStackTrace();
		}
		
		System.out.println("App url--->"+pro.getProperty("appURL"));
		System.out.println("browse --->"+pro.getProperty("browserType"));
		System.out.println("browse --->"+pro.getProperty("browserType1"));
		
	}

}
