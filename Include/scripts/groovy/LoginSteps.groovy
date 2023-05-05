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



class LoginSteps {
	/**
	 * The step definitions below match with Katalon sample Gherkin steps
	 */

	@Given("User open website alta shop")
	public void user_open_website_alta_shop() {
		WebUI.openBrowser('')
		WebUI.navigateToUrl(GlobalVariable.url)
	}

	@When("User make sure website alta shop has been successfully loaded")
	public void user_make_sure_website_alta_shop_has_been_successfully_loaded() {
		WebUI.delay(3)
		WebUI.verifyElementPresent(findTestObject('Object Repository/WEB/OR001 - VerifyAltaShopWeb'), 30)
		WebUI.takeScreenshot()
	}

	@When("User go to redirect login")
	public void user_go_to_redirect_login() {
		WebUI.navigateToUrl(GlobalVariable.login)
		WebUI.delay(3)
		WebUI.verifyElementPresent(findTestObject('Object Repository/WEB/OR006 - InputEmailLogin'), 30)
		WebUI.takeScreenshot()
	}

	@When("User input email {string} and password {string}")
	public void user_input_email_and_password(String email, String password) {
		WebUI.setText(findTestObject('Object Repository/WEB/OR006 - InputEmailLogin'), email)
		WebUI.setText(findTestObject('Object Repository/WEB/OR008 - InputPasswordLogin'), password)
		WebUI.takeScreenshot()
	}

	@Then("User click button login and has been login success")
	public void user_click_button_login_and_has_been_login_success() {
		WebUI.click(findTestObject('Object Repository/WEB/OR009 - BtnLogin'))
		WebUI.verifyElementPresent(findTestObject('Object Repository/WEB/OR010 - BtnIconLogin'), 30)
		WebUI.delay(3)
		WebUI.takeScreenshot()
	}

	@And("User go to redirect login to scenario failed and define (.*)")
	public void user_go_to_redirect_login_and_define_login_not_registered(String description) {
		WebUI.navigateToUrl(GlobalVariable.login)
		WebUI.comment(description)
		WebUI.takeScreenshot()
	}

	@And("User input data scenario failed based on data (.*) and (.*)")
	public void inputScenarioFailed(String email, String password) {
		WebUI.setText(findTestObject('Object Repository/WEB/OR006 - InputEmailLogin'), email)
		WebUI.setText(findTestObject('Object Repository/WEB/OR008 - InputPasswordLogin'), password)
	}

	@Then("User click button login")
	public void user_click_button_login_and_verify_by_status() {

		WebUI.click(findTestObject('Object Repository/WEB/OR009 - BtnLogin'))
		WebUI.verifyElementPresent(findTestObject('Object Repository/WEB/OR007 - AlertError'), 30)
		WebUI.takeScreenshot()
		WebUI.closeBrowser()
	}
}