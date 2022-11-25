package hardcodedtestcases;

import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import genericLibraries.AutoConstantPath;
import genericLibraries.ExcelFileUtility;
import genericLibraries.JavaUtility;
import genericLibraries.PropertyFileUtility;
import genericLibraries.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactTest {

	public static void main(String[] args) throws InterruptedException {
		
		ExcelFileUtility excel = new ExcelFileUtility();
		PropertyFileUtility property = new PropertyFileUtility();
		JavaUtility javaUtility = new JavaUtility();
		WebDriverUtility webDriver = new WebDriverUtility();
		
		property.propertyFileInitialization(AutoConstantPath.PROPERTY_FILE_PATH);
		excel.excelFileInitialization(AutoConstantPath.EXCEL_FILE_PATH);
		
	//	Random random = new Random();
   //	int randomNum = random.nextInt(100);
		String url = property.getDataFromPropertyFile("url");
		String timouts = property.getDataFromPropertyFile("timeouts");
		WebDriver driver = webDriver.openBrowserAndApplication(url, Long.parseLong(timouts));
		
		
	/*	WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://localhost:8888/");
		
		driver.manage().window().maximize();*/
		
		WebElement logo = driver.findElement(By.xpath("//img[@alt='logo']"));
		
		if(logo.isDisplayed()) {
			
			System.out.println("Pass: Login page displayed");
		}
		
		else {
			
			System.out.println("Fail: Login page not diaplayed");
		}
		
		String user_name = property.getDataFromPropertyFile("username");
		String password = property.getDataFromPropertyFile("password");
		
		
		driver.findElement(By.name("user_name")).sendKeys(user_name);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		if(driver.getTitle().contains("Home")) {
			
			System.out.println("Pass: Home Page displayed");
		}
		
		else {
			
			System.out.println("Fail: Home Page not displayed ");
		}
		
		driver.findElement(By.xpath("//a[.='Contacts']")).click();
		
		if(driver.getTitle().contains("Contacts")) {
			
			System.out.println("Pass: Contacts Page displayed");
		}
		else {
			
			System.out.println("Fail: Contacts Page not displayed");
		}
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		
		
		WebElement firstNameDropdown = driver.findElement(By.name("salutationtype"));
		
		Map<String,String> map = excel.fetchDataFromExcel("Create Contact", "TestData");
		
		webDriver.dropdown(firstNameDropdown,map.get("First Name Salutation"));
		
//		Select firstName = new Select(firstNameDropdown);		
//		firstName.selectByVisibleText("Mrs.");
		
		String last_Name = map.get("Last Name")+ javaUtility.generateRandomNumber(100);
		
		driver.findElement(By.name("lastname")).sendKeys(last_Name);
		
		String parent = webDriver.parentWindow();
		
		//String parent = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//td[@class='dvtCellLabel' and contains(.,'Organization')]/following-sibling::td/img")).click();	
		
		webDriver.handleChildBrowserPopUp("Organizations");
		
		/*Set<String> child = driver.getWindowHandles();
		
		System.out.println(child);
		
		for(String newWindow:child) {
		
			driver.switchTo().window(newWindow);
			
			Thread.sleep(1000);
			
			System.out.println("times");
			
		}*/
		
		String organizationName = map.get("Organization Name");
			
			if(driver.findElement(By.xpath("//td[@class='moduleName' and text()='Organizations']")).isDisplayed()) {
				
				driver.findElement(By.xpath("//tbody//tr/td//a[contains(.,organizationName)]")).click();						
			}
			
			webDriver.switchToWindow(parent);
			//driver.switchTo().window(parent);		
			
			
			driver.findElement(By.name("imagename")).sendKeys(map.get("Contact Image"));
			
			driver.findElement(By.xpath("//input[contains(@title,'Save')]")).click();
			
			String ContactInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			
			if(ContactInfo.contains(last_Name)) {
				
				System.out.println("Pass: Contact info updated");
			}
			
			else {
				
				System.out.println("Fail: Contact info not updated");
			}
			
			driver.findElement(By.xpath("//a[.='Contacts']")).click();
			
			String contactPage = driver.findElement(By.xpath("//a[@class='hdrLink']")).getText();
			
			if(contactPage.contains("Contacts")) {
				
				System.out.println("Pass: Contact page is displayed");
			}
			
			else {
				
				System.out.println("Fail:  Contact page is not displayed ");
			}
			
			String contactData = driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr[last()]/td[4]")).getText();
			
			if(contactData.contains(last_Name)) {
				
				System.out.println("Pass: Test case passed");
				excel.writeDataIntoExcel("Create Organization", "Pass", AutoConstantPath.EXCEL_FILE_PATH, "TestData");
			}
			else {
				
				System.out.println("Fail: Test case failed");
				excel.writeDataIntoExcel("Create Organization", "Fail", AutoConstantPath.EXCEL_FILE_PATH, "TestData");
			}
			
			WebElement administratorImage = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
			webDriver.mouseHoverToElement(administratorImage);
//			Actions a = new Actions(driver);
//			a.moveToElement(administratorImage).perform();
			driver.findElement(By.xpath("//a[.='Sign Out']")).click();
					
			webDriver.closeBrowser();
			excel.closeWorkbook();
			
			//driver.quit();			
}
	
}
