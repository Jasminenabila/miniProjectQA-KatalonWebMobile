import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import com.github.javafaker.Faker



class RegisterSteps {
	/**
	 * The step definitions below match with Katalon sample Gherkin steps
	 */


	Faker faker = new Faker();
	String nameFaker = faker.name().firstName(); // Miss Samanta Schmidt
	String lastNameFaker = faker.name().lastName();
	String passwordFaker = faker.internet().password();

	@Given("User open website alterra")
	public void user_open_website_alterra() {
		WebUI.openBrowser('')
		WebUI.navigateToUrl(GlobalVariable.url)
	}

	@When("User make sure website alterra has been loaded")
	public void user_make_sure_website_alterra_has_been_loaded() {
		WebUI.delay(3)
		WebUI.verifyElementPresent(findTestObject('Object Repository/WEB/OR001 - VerifyAltaShopWeb'), 30)
		WebUI.takeScreenshot()
	}

	@And("User go to register page and define scenario (.*)")
	public void goToRegisterPage(String description) {
		WebUI.comment(description)
		WebUI.navigateToUrl(GlobalVariable.register)
		WebUI.delay(3)
		WebUI.verifyElementPresent(findTestObject('Object Repository/WEB/OR002 - InputFullname'), 30)
		WebUI.takeScreenshot()
	}

	@And("User input form register (.*), (.*) and (.*) define (.*)")
	public void inputRegister(String fullname, String email, String password, String status) {
		if(status == 'positive') {
			WebUI.setText(findTestObject('Object Repository/WEB/OR002 - InputFullname'), fullname+''+nameFaker)
			WebUI.setText(findTestObject('Object Repository/WEB/OR003 - InputEmail'), email+lastNameFaker+'@alterra.com')
			WebUI.setText(findTestObject('Object Repository/WEB/OR004 - InputPassword'), password+' '+passwordFaker)
		}
		else {

			WebUI.setText(findTestObject('Object Repository/WEB/OR002 - InputFullname'), fullname)
			WebUI.setText(findTestObject('Object Repository/WEB/OR003 - InputEmail'), email+'@alterra.com')
			WebUI.setText(findTestObject('Object Repository/WEB/OR004 - InputPassword'), password)

		}

	}

	@Then("User click button register and verify based on (.*)")
	public void clickBtnRegister(String status) {
		WebUI.click(findTestObject('Object Repository/WEB/OR005 - BtnRegister'))

		if(status == 'positive') {
			WebUI.verifyElementPresent(findTestObject('Object Repository/WEB/OR006 - InputEmailLogin'), 30)
			WebUI.delay(2)
			WebUI.takeScreenshot()
		}
		else {
			WebUI.verifyElementPresent(findTestObject('Object Repository/WEB/OR007 - AlertError'), 30)
			WebUI.delay(2)
			WebUI.takeScreenshot()
		}

		WebUI.closeBrowser()
	}

}