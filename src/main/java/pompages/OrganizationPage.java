package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.WebDriverUtility;

public class OrganizationPage {
	
	@FindBy(xpath="//a[@class='hdrLink']")private WebElement organizationsPageHeader;
	@FindBy(xpath="//img[@title='Create Organization...']") private WebElement plusImage;		
	@FindBy(xpath="//table[@class='lvt small']/tbody/tr[last()]/td[3]")private WebElement newOrganization;
	
	public OrganizationPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}

	public String getOrganizationsPageHeader() {
		return organizationsPageHeader.getText();
	}
	
	public void clickPlusImage() {
		
		plusImage.click();
	}		
	
	public String getNewOrganization() {
		
		return newOrganization.getText();
	}
			
}