package testngimplementationtestcases;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.AutoConstantPath;
import genericLibraries.BaseClass;

public class CreateNewLeadTest extends BaseClass{

	@Test
	public void createNewLead() {
		
		SoftAssert soft = new SoftAssert();
		
		home.clickLeadPageLink();
		
		soft.assertTrue(lead.getLeadsPageHeader().contains("Leads"));
		
//		if(lead.getLeadsPageHeader().contains("Leads")) {
//			
//			System.out.println("Pass: Lead Page is displayed");
//		}
//		else {
//			
//			System.out.println("Fail: Lead Page is not displayed");
//		}
		
		lead.clickPlusImageLeadsPage();
		
		Map<String,String> map = excel.fetchDataFromExcel("Create Lead", "TestData");
		
		newLead.firstNameDropdown(webDriver, map.get("First Name Salutation"));
		
		String last_Name = map.get("Last Name");
		String company = map.get("Company");
		
		newLead.getLastName(last_Name);
		newLead.getCompany(company);
		newLead.clickSaveButtonLeadsPage();
		
		soft.assertTrue(leadInfo.getLeadInfo().contains(last_Name));
		
//		if(leadInfo.getLeadInfo().contains(last_Name)) {
//			
//			System.out.println("Pass: Lead info is updated");
//		}
//		
//		else {
//			
//			System.out.println("Fail: Lead info is not updated");
//		}

         leadInfo.clickDuplicateTab();
         
         soft.assertTrue(duplicate.getDuplicatePageHeader().contains(last_Name));
		
//		if(duplicate.getDuplicatePageHeader().contains(last_Name)) {
//			
//			System.out.println("Pass: Duplicate page displayed");
//		}
//		else {
//			
//			System.out.println("Fail: Duplicate page is not displayed");
//		}
		
		String NewLastName = map.get("New Last Name");
		
		duplicate.setLastName(NewLastName);
		
		duplicate.clickSaveButtonDuplicatePage();
		
		soft.assertTrue(modifiedLead.getModifiedLead().contains(NewLastName));
		
//		if(modifiedLead.getModifiedLead().contains(NewLastName)) {
//			
//			System.out.println("Pass: Lead info modified sucessfully");
//		}
//		else {
//			
//			System.out.println("Fail: Lead info not modified");
//		}

        home.clickLeadPageLink();
        
        soft.assertTrue(lead.getLeadsPageHeader().contains("Leads"));
		
//		if(lead.getLeadsPageHeader().contains("Leads")) {
//			
//			System.out.println("Pass: Lead Page is displayed");
//		}
//		else {
//			
//			System.out.println("Fail: Lead Page is not displayed");
//		}
		
        soft.assertTrue(lead.getPreviousLeadData().contains(last_Name));
        
//		if(lead.getPreviousLeadData().contains(last_Name)) {
//			
//			System.out.println("Pass: Previous lead data is present");			
//		}
//		else {
//			
//			System.out.println("Fail: Previous lead data not present");
//		}
		
        soft.assertTrue(lead.getModifiedLeadData().contains(NewLastName));
        
		if(lead.getModifiedLeadData().contains(NewLastName)) {
			
			System.out.println("Pass: Test case passed");	
			excel.writeDataIntoExcel("Create Lead", "Pass", AutoConstantPath.EXCEL_FILE_PATH, "TestData");
		}
		else {
			
			System.out.println("Fail: Test case failed");
			excel.writeDataIntoExcel("Create Lead", "Fail", AutoConstantPath.EXCEL_FILE_PATH, "TestData");
		}			
        
        soft.assertAll();
	}
}
