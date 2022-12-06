package testngimplementationtestcases;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.AutoConstantPath;
import genericLibraries.BaseClass;

public class CreateOrganizationTest extends BaseClass {

	@Test
	public void createOrganization() throws InterruptedException {	
		
		SoftAssert soft = new SoftAssert();
		
		home.clickOrganizationPageLink();

		String organizationsPageHeader = organization.getOrganizationsPageHeader();
		
		soft.assertTrue(organizationsPageHeader.contains("Organizations"));
		
//		if(organizationsPageHeader.contains("Organizations"))
//			System.out.println("Pass: Organizations page displayed");
//		else
//			System.out.println("Fail: Organizations page not found");
		
		organization.clickPlusImage();
		
		String createOrganizationPageHeader = newOrganization.getCreateOrganizationPageHeader();
		
		soft.assertTrue(createOrganizationPageHeader.contains("Organization"));
		
//		if(createOrganizationPageHeader.contains("Organization"))
//			System.out.println("Pass: Create New Organization page is displayed");
//		else
//			System.out.println("Fail: Create new organization page not found");
		
		Map<String,String> map = excel.fetchDataFromExcel("Create Organization", "TestData");
		
		String accountName = map.get("Organization Name")+ javaUtility.generateRandomNumber(100);		
		newOrganization.enter_OrganizationName(accountName);
	
		newOrganization.getIndustryDropdown(webDriver, map.get("Industry"));
		
		newOrganization.clickGroup();
		
		newOrganization.getassignedToDropdown(webDriver, map.get("Group"));
		
		newOrganization.clickSave();
		
		String newOrganizationInfo = organizationInfo.getNewOrganizationInfo();
		
		soft.assertTrue(newOrganizationInfo.contains(accountName));
//		
//		if(newOrganizationInfo.contains(accountName))
//			System.out.println("Pass: New Organization Info page displayed");
//		else
//			System.out.println("Fail: New Organization Info not found");
		
		home.clickOrganizationPageLink();
		
		soft.assertTrue(organization.getNewOrganization().contains(accountName));
		
		if(organization.getNewOrganization().contains(accountName)) {
			System.out.println("Test case passed");
			excel.writeDataIntoExcel("Create Organization", "Pass", AutoConstantPath.EXCEL_FILE_PATH, "TestData");
		}
		else {
			System.out.println("Test case failed");
			excel.writeDataIntoExcel("Create Organization", "Fail", AutoConstantPath.EXCEL_FILE_PATH, "TestData");
		}
		
		soft.assertAll();
		
		}
	}
