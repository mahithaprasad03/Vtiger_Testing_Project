package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.WebDriverUtility;

public class HomePage {
	
	@FindBy(xpath="//a[@class='hdrLink']")private WebElement homePageHeader;
	@FindBy(xpath="//a[.='Organizations']")private WebElement organizationPageLink;
	@FindBy(xpath="//a[.='Contacts']") private WebElement contactPageLink;
	@FindBy(xpath="//a[.='Leads']") private WebElement leadPageLink;
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']") private WebElement administratorImage;
	@FindBy(xpath="//a[.='Sign Out']") private WebElement signOutLink;
	
	public HomePage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	public String getHomePageHeader() {
		
		return homePageHeader.getText();
	}
	
	public void clickOrganizationPageLink() {
		
		organizationPageLink.click();
	}
	
	public void clickContactPageLink() {
		
		contactPageLink.click();
	}
	
	public void clickLeadPageLink() {
		
		leadPageLink.click();
	}	
	
	public void clickSignOut(WebDriverUtility webDriver) {
		
		webDriver.mouseHoverToElement(administratorImage);		
		signOutLink.click();
	}
}
