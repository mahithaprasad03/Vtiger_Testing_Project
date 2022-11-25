package pomimplementationtestcases;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import genericLibraries.AutoConstantPath;
import genericLibraries.ExcelFileUtility;
import genericLibraries.JavaUtility;
import genericLibraries.PropertyFileUtility;
import genericLibraries.WebDriverUtility;
import pompages.CreatingNewLeadPage;
import pompages.DuplicatePage;
import pompages.HomePage;
import pompages.LeadInfoPage;
import pompages.LeadsPage;
import pompages.LoginPage;
import pompages.ModifiedLeadInfoPage;

public class CreateNewLeadTest {

	public static void main(String[] args) {
		
		ExcelFileUtility excel = new ExcelFileUtility();
		PropertyFileUtility property = new PropertyFileUtility();
		JavaUtility javaUtility = new JavaUtility();
		WebDriverUtility webDriver = new WebDriverUtility();
		
		property.propertyFileInitialization(AutoConstantPath.PROPERTY_FILE_PATH);
		excel.excelFileInitialization(AutoConstantPath.EXCEL_FILE_PATH);
		
		String url = property.getDataFromPropertyFile("url");
		String timouts = property.getDataFromPropertyFile("timeouts");
		WebDriver driver = webDriver.openBrowserAndApplication(url, Long.parseLong(timouts));
		
		LoginPage login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		LeadsPage lead = new LeadsPage(driver);
		CreatingNewLeadPage newLead = new CreatingNewLeadPage(driver);
		LeadInfoPage leadInfo = new LeadInfoPage(driver);
		DuplicatePage duplicate = new DuplicatePage(driver);
		ModifiedLeadInfoPage modifiedLead = new ModifiedLeadInfoPage(driver);
		
		
		WebElement logo = login.getLoginLogo();
		
		if(logo.isDisplayed()) {
			
			System.out.println("Pass: Login page displayed");
		}
		
		else {
			
			System.out.println("Fail: Login page not diaplayed");
		}
		
		String user_name = property.getDataFromPropertyFile("username");
		String password = property.getDataFromPropertyFile("password");
		
		login.login(user_name, password);
		
		if(driver.getTitle().contains("Home")) {
			
			System.out.println("Pass: Home Page displayed");
		}
		
		else {
			
			System.out.println("Fail: Home Page not displayed ");
		}
		
		home.clickLeadPageLink();
		
		if(lead.getLeadsPageHeader().contains("Leads")) {
			
			System.out.println("Pass: Lead Page is displayed");
		}
		else {
			
			System.out.println("Fail: Lead Page is not displayed");
		}
		
		lead.clickPlusImageLeadsPage();
		
		Map<String,String> map = excel.fetchDataFromExcel("Create Lead", "TestData");
		
		newLead.firstNameDropdown(webDriver, map.get("First Name Salutation"));
		
		String last_Name = map.get("Last Name");
		String company = map.get("Company");
		
		newLead.getLastName(last_Name);
		newLead.getCompany(company);
		newLead.clickSaveButtonLeadsPage();
		
		if(leadInfo.getLeadInfo().contains(last_Name)) {
			
			System.out.println("Pass: Lead info is updated");
		}
		
		else {
			
			System.out.println("Fail: Lead info is not updated");
		}

         leadInfo.clickDuplicateTab();
		
		if(duplicate.getDuplicatePageHeader().contains(last_Name)) {
			
			System.out.println("Pass: Duplicate page displayed");
		}
		else {
			
			System.out.println("Fail: Duplicate page is not displayed");
		}
		
		String NewLastName = map.get("New Last Name");
		
		duplicate.setLastName(NewLastName);
		
		duplicate.clickSaveButtonDuplicatePage();
		
		if(modifiedLead.getModifiedLead().contains(NewLastName)) {
			
			System.out.println("Pass: Lead info modified sucessfully");
		}
		else {
			
			System.out.println("Fail: Lead info not modified");
		}

        home.clickLeadPageLink();
		
		if(lead.getLeadsPageHeader().contains("Leads")) {
			
			System.out.println("Pass: Lead Page is displayed");
		}
		else {
			
			System.out.println("Fail: Lead Page is not displayed");
		}
		
		if(lead.getPreviousLeadData().contains(last_Name)) {
			
			System.out.println("Pass: Previous lead data is present");			
		}
		else {
			
			System.out.println("Fail: Previous lead data not present");
		}
		
		if(lead.getModifiedLeadData().contains(NewLastName)) {
			
			System.out.println("Pass: Modified lead data is present");			
		}
		else {
			
			System.out.println("Fail: Modified lead data not present");
		}
		home.clickSignOut(webDriver);
				
		webDriver.closeBrowser();
		excel.closeWorkbook();		

	}

}
