package genericLibraries;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import pompages.ContactsInfoPage;
import pompages.CreateContactPage;
import pompages.CreateNewOrganizationPage;
import pompages.CreatingNewContactPage;
import pompages.CreatingNewLeadPage;
import pompages.DuplicatePage;
import pompages.HomePage;
import pompages.LeadInfoPage;
import pompages.LeadsPage;
import pompages.LoginPage;
import pompages.ModifiedLeadInfoPage;
import pompages.NewOrganizationInfoPage;
import pompages.OrganizationPage;
import pompages.OrganizationsPopUpPage;

public class BaseClass {
	
	protected ExcelFileUtility excel;
	protected JavaUtility javaUtility;
	protected PropertyFileUtility property;
	protected WebDriverUtility webDriver;
	protected LoginPage login;
	protected HomePage home;
	protected OrganizationPage organization;
	protected CreateNewOrganizationPage newOrganization; 
	protected NewOrganizationInfoPage organizationInfo;
	protected CreateContactPage contact;
	protected CreatingNewContactPage newContact;
	protected ContactsInfoPage contactInfo;
	protected OrganizationsPopUpPage popup;
	protected WebDriver driver; 
	protected LeadsPage lead;
	protected CreatingNewLeadPage newLead;
	protected LeadInfoPage leadInfo;
	protected DuplicatePage duplicate; 
	protected ModifiedLeadInfoPage modifiedLead;
	
	//@BeforeSuite
	@BeforeTest
	public void initializeGenericUtility() {
		
		 excel = new ExcelFileUtility();
		 javaUtility = new JavaUtility();
		 property = new PropertyFileUtility();
		 webDriver = new WebDriverUtility();	
		 
		 property.propertyFileInitialization(AutoConstantPath.PROPERTY_FILE_PATH);
		 excel.excelFileInitialization(AutoConstantPath.EXCEL_FILE_PATH);
	}
	@BeforeClass
	public void launchBrowserAndInitializePom() {
		
		String url = property.getDataFromPropertyFile("url");
		String timouts = property.getDataFromPropertyFile("timeouts");
		driver = webDriver.openBrowserAndApplication(url, Long.parseLong(timouts));
		
	    login = new LoginPage(driver);
	    
	    WebElement logo = login.getLoginLogo();	
	    
	    Assert.assertTrue(logo.isDisplayed());
//		if(logo.isDisplayed())
//			System.out.println("Pass: Login page displayed");
//		else
//			System.out.println("Fail: Login page not found");
		
	    home = new HomePage(driver);
	    organization = new OrganizationPage(driver);
	    newOrganization = new CreateNewOrganizationPage(driver);
	    organizationInfo = new NewOrganizationInfoPage(driver);	
	    contact = new CreateContactPage(driver);
		newContact = new CreatingNewContactPage(driver);
		contactInfo = new ContactsInfoPage(driver);
		popup = new OrganizationsPopUpPage(driver);
		lead = new LeadsPage(driver);
		newLead = new CreatingNewLeadPage(driver);
		leadInfo = new LeadInfoPage(driver);
		duplicate = new DuplicatePage(driver);
		modifiedLead = new ModifiedLeadInfoPage(driver);
	}
	
	@BeforeMethod
	public void loginIntoApplication() {
		
		String user_name = property.getDataFromPropertyFile("username");
		String password = property.getDataFromPropertyFile("password");
		
		login.login(user_name, password);
		
		String homePageHeader = home.getHomePageHeader();
		
		Assert.assertTrue(homePageHeader.contains("Home"));
		
//		if(homePageHeader.contains("Home"))
//			System.out.println("Pass: Home page is displayed");
//		else
//			System.out.println("Fail: Home page not found");
		
	}
	
	//@Test
	
	@AfterMethod
	public void logoutFromApplication() {
		
		home.clickSignOut(webDriver);
	}
	@AfterClass
	public void closeBrowser() {
		
		webDriver.closeBrowser();
	}
	@AfterTest
	public void closeWorkbook() {
		excel.closeWorkbook();
	}
	//@AfterSuite
	
	

}
