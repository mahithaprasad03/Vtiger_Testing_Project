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
import pompages.ContactsInfoPage;
import pompages.CreateContactPage;
import pompages.CreatingNewContactPage;
import pompages.HomePage;
import pompages.LoginPage;
import pompages.OrganizationsPopUpPage;

public class CreateContactTest {

	public static void main(String[] args) throws InterruptedException {
		
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
		CreateContactPage contact = new CreateContactPage(driver);
		CreatingNewContactPage newContact = new CreatingNewContactPage(driver);
		ContactsInfoPage contactInfo = new ContactsInfoPage(driver);
		OrganizationsPopUpPage popup = new OrganizationsPopUpPage(driver);
		
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
		
		home.clickContactPageLink();
		
		if(driver.getTitle().contains("Contacts")) {
			
			System.out.println("Pass: Contacts Page displayed");
		}
		else {
			
			System.out.println("Fail: Contacts Page not displayed");
		}
		
		contact.clickNewContactPlusImage();
		Map<String,String> map = excel.fetchDataFromExcel("Create Contact", "TestData");
		
		newContact.clickFirstNameDropdown(webDriver, map.get("First Name Salutation"));		
		
		String last_Name = map.get("Last Name")+ javaUtility.generateRandomNumber(100);
		
		newContact.enter_LastName(last_Name);
		
		String parent = webDriver.parentWindow();
		
		newContact.clickPlusImageToSelectOrganization();	
		
		webDriver.handleChildBrowserPopUp("Organizations");
		
		String organizationName = map.get("Organization Name");
			
			if(popup.getOrganizationPopUpPageHeader().contains("Organizations")) {
				
				driver.findElement(By.xpath("//tbody//tr/td//a[contains(.,organizationName)]")).click();						
			}
			
			webDriver.switchToWindow(parent);	
			
			newContact.getContactImage(map.get("Contact Image"));
			
			newContact.clickSaveButtonInContacts();
			
			if(contactInfo.getContactsInfoPageHeader().contains(last_Name)) {
				
				System.out.println("Pass: Contact info updated");
			}
			
			else {
				
				System.out.println("Fail: Contact info not updated");
			}
			
			
			home.clickContactPageLink();
			
			if(contact.getcontactPageHeader().contains("Contacts")) {
				
				System.out.println("Pass: Contact page is displayed");
			}
			
			else {
				
				System.out.println("Fail:  Contact page is not displayed ");
			}
			
			if(contact.getNewContact().contains(last_Name)) {
				
				System.out.println("Pass: Test case passed");
				excel.writeDataIntoExcel("Create Organization", "Pass", AutoConstantPath.EXCEL_FILE_PATH, "TestData");
			}
			else {
				
				System.out.println("Fail: Test case failed");
				excel.writeDataIntoExcel("Create Organization", "Fail", AutoConstantPath.EXCEL_FILE_PATH, "TestData");
			}
			
			home.clickSignOut(webDriver);
			webDriver.closeBrowser();
			excel.closeWorkbook();			
}
	
}
