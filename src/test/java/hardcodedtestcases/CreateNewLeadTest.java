package hardcodedtestcases;

import java.util.Map;

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

public class CreateNewLeadTest {

	public static void main(String[] args) {
		
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
		
		
		/*WebDriverManager.chromedriver().setup();
		
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
		
		driver.findElement(By.xpath("//a[.='Leads']")).click();
		
		String LeadPage = driver.findElement(By.xpath("//a[@class='hdrLink']")).getText();
		
		if(LeadPage.contains("Leads")) {
			
			System.out.println("Pass: Lead Page is displayed");
		}
		else {
			
			System.out.println("Fail: Lead Page is not displayed");
		}
		
		driver.findElement(By.xpath("//img[@alt='Create Lead...']")).click();
		
		WebElement firstNameDropdown = driver.findElement(By.name("salutationtype"));
		
		Map<String,String> map = excel.fetchDataFromExcel("Create Lead", "TestData");
		
		webDriver.dropdown(firstNameDropdown, map.get("First Name Salutation"));
		
//		Select firstName = new Select(firstNameDropdown);		
//		firstName.selectByVisibleText("Mrs.");
		
		String last_Name = map.get("Last Name");
		String company = map.get("Company");
		
		driver.findElement(By.name("lastname")).sendKeys(last_Name);
		driver.findElement(By.name("company")).sendKeys(company);
		driver.findElement(By.xpath("//input[contains(@title,'Save')]")).click();
		
		String leadInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(leadInfo.contains(last_Name)) {
			
			System.out.println("Pass: Lead info is updated");
		}
		
		else {
			
			System.out.println("Fail: Lead info is not updated");
		}
		driver.findElement(By.xpath("//input[contains(@title,'Duplicate')]")).click();
		
		String duplicatepage = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		
		if(duplicatepage.contains(last_Name)) {
			
			System.out.println("Pass: Duplicate page displayed");
		}
		else {
			
			System.out.println("Fail: Duplicate page is not displayed");
		}
		
		String NewLastName = map.get("New Last Name");
		WebElement lastName = driver.findElement(By.name("lastname"));
		lastName.clear();
		lastName.sendKeys(NewLastName);
		driver.findElement(By.xpath("//input[contains(@title,'Save')]")).click();
		
		String modifiedLead = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(modifiedLead.contains(NewLastName)) {
			
			System.out.println("Pass: Lead info modified sucessfully");
		}
		else {
			
			System.out.println("Fail: Lead info not modified");
		}
		driver.findElement(By.xpath("//a[.='Leads']")).click();
		
		String LeadUpdatedPage = driver.findElement(By.xpath("//a[@class='hdrLink']")).getText();
		
		if(LeadUpdatedPage.contains("Leads")) {
			
			System.out.println("Pass: Lead Page is displayed");
		}
		else {
			
			System.out.println("Fail: Lead Page is not displayed");
		}
		
		String previousLeadData = driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr[last()-1]/td[3]")).getText();
		
		if(previousLeadData.contains(last_Name)) {
			
			System.out.println("Pass: Previous lead data is present");			
		}
		else {
			
			System.out.println("Fail: Previous lead data not present");
		}
		
		String modifiedLeadData = driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr[last()]/td[3]")).getText();
		
		if(modifiedLeadData.contains(NewLastName)) {
			
			System.out.println("Pass: Modified lead data is present");			
		}
		else {
			
			System.out.println("Fail: Modified lead data not present");
		}
		
		WebElement administratorImage = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		webDriver.mouseHoverToElement(administratorImage);
//		Actions a = new Actions(driver);
//		a.moveToElement(administratorImage).perform();
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
				
		webDriver.closeBrowser();
		excel.closeWorkbook();
		
		//driver.quit();			

	}

}
