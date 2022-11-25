package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	@FindBy(xpath="//img[@alt='logo']")private WebElement loginLogo;
	@FindBy(name="user_name")private WebElement loginUserName;
	@FindBy(name="user_password")private WebElement loginPassword;
	@FindBy(id="submitButton")private WebElement loginButton;
	
	public LoginPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}

	public WebElement getLoginLogo() {
		return loginLogo;
	}
	
	public void login(String username,String password) {
		
		loginUserName.sendKeys(username);
		loginPassword.sendKeys(password);
		loginButton.click();
	}
}
