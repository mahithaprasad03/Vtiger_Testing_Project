package genericLibraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * This class contains all reusable methods to perform operations on properties file
 * @author mahit
 *
 */

public class PropertyFileUtility {
	
	private Properties property;
	
	public void propertyFileInitialization(String propertyPath) {
		
		FileInputStream fis = null;
	
		try {
			fis = new FileInputStream(propertyPath);
		} 
		catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}

	property = new Properties();
	
	try {
		property.load(fis);
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	
	}
	
	/**
	 * This method is used to fetch data from properties file
	 * @param key
	 * @return
	 */
	
	public String getDataFromPropertyFile(String key){
		
		String data = property.getProperty(key);
		return data;
		
	}	
	 
}
