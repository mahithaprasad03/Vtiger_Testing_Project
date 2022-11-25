package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.WebDriverUtility;

public class CreatingNewLeadPage {
	
	@FindBy(xpath="//span[@class='lvtHeaderText']")private WebElement createNewLeadPageHeader;
	@FindBy(name="salutationtype")private WebElement firstNameDropdown;
	@FindBy(name="lastname")private WebElement lastName;
	@FindBy(name="company")private WebElement company;
	@FindBy(xpath="//input[contains(@title,'Save')]")private WebElement saveButtonLeadsPage;
	
public CreatingNewLeadPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	public String getCreateNewLeadPageHeader() {
		
		return createNewLeadPageHeader.getText();
	}

	public void firstNameDropdown(WebDriverUtility webdriver, String first_Name) {
		
		webdriver.dropdown(firstNameDropdown, first_Name);
	}
	
	public void getLastName(String last_Name) {
		
		lastName.sendKeys(last_Name);
	}
	
	public void getCompany(String companyName) {
		
		company.sendKeys(companyName);
	}
	
	public void clickSaveButtonLeadsPage() {
		
		saveButtonLeadsPage.click();
	}
}
