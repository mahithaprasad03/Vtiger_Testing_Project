package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPopUpPage {
	
	@FindBy(xpath="//td[@class='moduleName' and text()='Organizations']")private WebElement organizationPopUpPageHeader;
	@FindBy(xpath="//tbody//tr/td//a[contains(.,organizationName)]")private WebElement organizationNameInTable;
	
public OrganizationsPopUpPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	public String getOrganizationPopUpPageHeader() {
		
		return organizationPopUpPageHeader.getText();
	}
	
	public void clickOrganizationNameInTable() {
		
		organizationNameInTable.click();
		
	}	

}
