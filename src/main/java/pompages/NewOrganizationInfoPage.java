package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewOrganizationInfoPage {
	
	@FindBy(xpath="//span[@class='dvHeaderText']") private WebElement newOrganizationInfo;
	
public NewOrganizationInfoPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	
	public String getNewOrganizationInfo() {
		return newOrganizationInfo.getText();
	}

}
