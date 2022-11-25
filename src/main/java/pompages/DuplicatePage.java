package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DuplicatePage {
	
	@FindBy(xpath="//span[@class='lvtHeaderText']")private WebElement duplicatePageHeader;
	@FindBy(name="lastname")private WebElement lastName;
	@FindBy(xpath="//input[contains(@title,'Save')]")private WebElement saveButtonDuplicatePage;
	
public DuplicatePage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	public String getDuplicatePageHeader() {
		
		return duplicatePageHeader.getText();
	}
	
	public void setLastName(String NewLastName) {
		
		lastName.clear();
		
		lastName.sendKeys(NewLastName);
	}
	
	public void clickSaveButtonDuplicatePage() {
		
		saveButtonDuplicatePage.click();
	}

}
