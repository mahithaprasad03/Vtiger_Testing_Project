package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.WebDriverUtility;

public class CreateNewOrganizationPage {
	
	@FindBy(xpath="//span[@class='lvtHeaderText']")private WebElement createOrganizationPageHeader;
	@FindBy(name="accountname")private WebElement organizationName;
	@FindBy(name="industry") private WebElement industryDropdown;
	@FindBy(xpath="//input[@value='T']") private WebElement group;
	@FindBy(xpath="//select[@name='assigned_group_id']")private WebElement assignedToDropdown;
	@FindBy(xpath="//input[contains(@value,'Save')]") private WebElement saveButton;
	
public CreateNewOrganizationPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	
	public String getCreateOrganizationPageHeader() {
		
		return createOrganizationPageHeader.getText();
	}
	
	public void enter_OrganizationName(String accountName) {
		
		organizationName.sendKeys(accountName);
	}
	
//	public WebElement setOrganizationName() {
//		return organizationName;
//	}
	
	public void getIndustryDropdown(WebDriverUtility webDriver,String value) {
		
		webDriver.dropdown(industryDropdown,value);
	}
	
	public void clickGroup() {
		
		group.click();
	}
	
	public void getassignedToDropdown(WebDriverUtility webDriver,String value) {
		
		webDriver.dropdown(assignedToDropdown, value);
	}

	public void clickSave() {
		
		saveButton.click();
	}
}
