package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadInfoPage {
	
	@FindBy(xpath="//span[@class='dvHeaderText']")private WebElement leadInfo;
	@FindBy(xpath="//input[contains(@title,'Duplicate')]")private WebElement duplicateTab;
	
public LeadInfoPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	public String getLeadInfo() {
		
		return leadInfo.getText();
	}
	
	public void clickDuplicateTab() {
		
		duplicateTab.click();
	}

}
