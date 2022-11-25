package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsInfoPage {
	
	@FindBy(xpath="//span[@class='dvHeaderText']")private WebElement contactsInfoPageHeader;
	
	public ContactsInfoPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	
	public String getContactsInfoPageHeader() {
		
		return contactsInfoPageHeader.getText();
	}
	

}
