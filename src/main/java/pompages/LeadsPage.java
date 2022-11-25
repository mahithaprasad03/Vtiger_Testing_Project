package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadsPage {
	
	@FindBy(xpath="//a[@class='hdrLink']")private WebElement leadsPageHeader;
	@FindBy(xpath="//img[@alt='Create Lead...']")private WebElement plusImageLeadsPage;
	@FindBy(xpath="//table[@class='lvt small']/tbody/tr[last()-1]/td[3]")private WebElement previousLeadData;
	@FindBy(xpath="//table[@class='lvt small']/tbody/tr[last()]/td[3]")private WebElement modifiedLeadData;
	
public LeadsPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	public String getLeadsPageHeader() {
		
		return leadsPageHeader.getText();
	}
	
	public void clickPlusImageLeadsPage() {
		
		plusImageLeadsPage.click();
	}
	
	public String getPreviousLeadData() {
		
		return previousLeadData.getText();
	}
	
	public String getModifiedLeadData() {
		
		return modifiedLeadData.getText();
	}
	
	

}
