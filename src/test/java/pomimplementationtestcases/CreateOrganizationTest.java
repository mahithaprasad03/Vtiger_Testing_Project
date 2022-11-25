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
import pompages.CreateNewOrganizationPage;
import pompages.HomePage;
import pompages.LoginPage;
import pompages.NewOrganizationInfoPage;
import pompages.OrganizationPage;

public class CreateOrganizationTest {

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
	    OrganizationPage organization = new OrganizationPage(driver);
	    CreateNewOrganizationPage newOrganization = new CreateNewOrganizationPage(driver);
	    NewOrganizationInfoPage organizationInfo = new NewOrganizationInfoPage(driver);		    
		
		WebElement logo = login.getLoginLogo();		
		if(logo.isDisplayed())
			System.out.println("Pass: Login page displayed");
		else
			System.out.println("Fail: Login page not found");
		
		String user_name = property.getDataFromPropertyFile("username");
		String password = property.getDataFromPropertyFile("password");
		
		login.login(user_name, password);
		
		String homePageHeader = home.getHomePageHeader();
		if(homePageHeader.contains("Home"))
			System.out.println("Pass: Home page is displayed");
		else
			System.out.println("Fail: Home page not found");
		
		home.clickOrganizationPageLink();

		String organizationsPageHeader = organization.getOrganizationsPageHeader();
		
		if(organizationsPageHeader.contains("Organizations"))
			System.out.println("Pass: Organizations page displayed");
		else
			System.out.println("Fail: Organizations page not found");
		
		organization.clickPlusImage();
		
		String createOrganizationPageHeader = newOrganization.getCreateOrganizationPageHeader();
		
		if(createOrganizationPageHeader.contains("Organization"))
			System.out.println("Pass: Create New Organization page is displayed");
		else
			System.out.println("Fail: Create new organization page not found");
		
		Map<String,String> map = excel.fetchDataFromExcel("Create Organization", "TestData");
		
		String accountName = map.get("Organization Name")+ javaUtility.generateRandomNumber(100);		
		newOrganization.enter_OrganizationName(accountName);
	
		newOrganization.getIndustryDropdown(webDriver, map.get("Industry"));
		
		newOrganization.clickGroup();
		
		newOrganization.getassignedToDropdown(webDriver, map.get("Group"));
		
		newOrganization.clickSave();
		
		String newOrganizationInfo = organizationInfo.getNewOrganizationInfo();
		if(newOrganizationInfo.contains(accountName))
			System.out.println("Pass: New Organization Info page displayed");
		else
			System.out.println("Fail: New Organization Info not found");
		
		home.clickOrganizationPageLink();
		
		if(organization.getNewOrganization().contains(accountName)) {
			System.out.println("Test case passed");
			excel.writeDataIntoExcel("Create Organization", "Pass", AutoConstantPath.EXCEL_FILE_PATH, "TestData");
		}
		else {
			System.out.println("Test case failed");
			excel.writeDataIntoExcel("Create Organization", "Fail", AutoConstantPath.EXCEL_FILE_PATH, "TestData");
		}
		
		home.clickSignOut(webDriver);
		webDriver.closeBrowser();
		excel.closeWorkbook();
		
		}
}
