package testngimplementationtestcases;

import java.util.Map;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.AutoConstantPath;
import genericLibraries.BaseClass;

public class CreateContactTest extends BaseClass{

	@Test
	public void createContact() throws InterruptedException {
		
		SoftAssert soft = new SoftAssert();
		
		home.clickContactPageLink();
		
		soft.assertTrue(driver.getTitle().contains("Contacts"));
		
//		if(driver.getTitle().contains("Contacts")) {
//			
//			System.out.println("Pass: Contacts Page displayed");
//		}
//		else {
//			
//			System.out.println("Fail: Contacts Page not displayed");
//		}
		
		contact.clickNewContactPlusImage();
		Map<String,String> map = excel.fetchDataFromExcel("Create Contact", "TestData");
		
		newContact.clickFirstNameDropdown(webDriver, map.get("First Name Salutation"));		
		
		String last_Name = map.get("Last Name")+ javaUtility.generateRandomNumber(100);
		
		newContact.enter_LastName(last_Name);
		
		String parent = webDriver.parentWindow();
		
		newContact.clickPlusImageToSelectOrganization();	
		
		webDriver.handleChildBrowserPopUp("Organizations");
		
		String organizationName = map.get("Organization Name");
		
		soft.assertTrue(popup.getOrganizationPopUpPageHeader().contains("Organizations"));
			
			if(popup.getOrganizationPopUpPageHeader().contains("Organizations")) {
				
				driver.findElement(By.xpath("//tbody//tr/td//a[contains(.,organizationName)]")).click();						
			}
			
			webDriver.switchToWindow(parent);	
			
			newContact.getContactImage(map.get("Contact Image"));
			
			newContact.clickSaveButtonInContacts();
			
			soft.assertTrue(contactInfo.getContactsInfoPageHeader().contains(last_Name));
			
//			if(contactInfo.getContactsInfoPageHeader().contains(last_Name)) {
//				
//				System.out.println("Pass: Contact info updated");
//			}
//			
//			else {
//				
//				System.out.println("Fail: Contact info not updated");
//			}
			
			
			home.clickContactPageLink();
			
			soft.assertTrue(contact.getcontactPageHeader().contains("Contacts"));
			
//			if(contact.getcontactPageHeader().contains("Contacts")) {
//				
//				System.out.println("Pass: Contact page is displayed");
//			}
//			
//			else {
//				
//				System.out.println("Fail:  Contact page is not displayed ");
//			}
			
			soft.assertTrue(contact.getNewContact().contains(last_Name));
			
			if(contact.getNewContact().contains(last_Name)) {
				
				System.out.println("Pass: Test case passed");
				excel.writeDataIntoExcel("Create Organization", "Pass", AutoConstantPath.EXCEL_FILE_PATH, "TestData");
			}
			else {
				
				System.out.println("Fail: Test case failed");
				excel.writeDataIntoExcel("Create Organization", "Fail", AutoConstantPath.EXCEL_FILE_PATH, "TestData");
			}
			
			soft.assertAll();
}
	
}
