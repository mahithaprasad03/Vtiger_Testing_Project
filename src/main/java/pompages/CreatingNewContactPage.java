package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.WebDriverUtility;

public class CreatingNewContactPage {
	
	@FindBy(xpath="//span[@class='lvtHeaderText']")private WebElement creatingNewContactPageHeader;
	@FindBy(name="salutationtype")private WebElement firstNameDropdown;
	@FindBy(name="lastname")private WebElement lastName;
	@FindBy(xpath="//td[@class='dvtCellLabel' and contains(.,'Organization')]/following-sibling::td/img")
	private WebElement plusImageToSelectOrganization;
	@FindBy(name="imagename")private WebElement contactImage;
	@FindBy(xpath="//input[contains(@title,'Save')]")private WebElement saveButtonInContacts;
	
	
public CreatingNewContactPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	public String creatingNewContactPageHeader() {
		
		return creatingNewContactPageHeader.getText();
	}

	public void clickFirstNameDropdown(WebDriverUtility webdriver,String value) {
		
		webdriver.dropdown(firstNameDropdown, value);
	}
	
	public void enter_LastName(String last_Name) {
		
		lastName.sendKeys(last_Name);
	}
	
	public void clickPlusImageToSelectOrganization() {
		
		plusImageToSelectOrganization.click();
		
	}	
	
	public void getContactImage(String imageAddress) {
		
		contactImage.sendKeys(imageAddress);
	}
	
	public void clickSaveButtonInContacts() {
		
		saveButtonInContacts.click();
	}

}
