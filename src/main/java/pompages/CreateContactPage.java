package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.WebDriverUtility;

public class CreateContactPage {
	
	
	@FindBy(xpath="//a[@class='hdrLink']")private WebElement contactPageHeader;
	
	@FindBy(xpath="//table[@class='lvt small']/tbody/tr[last()]/td[4]")private WebElement newContact;
	
	@FindBy(xpath="//img[@alt='Create Contact...']")private WebElement newContactPlusImage;	
	
public CreateContactPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	
	public String getcontactPageHeader() {
		
		return contactPageHeader.getText();
	}
	
	public String getNewContact() {
		
		return newContact.getText();
	}
	
	public void clickNewContactPlusImage() {
		
		newContactPlusImage.click();
	}
}
