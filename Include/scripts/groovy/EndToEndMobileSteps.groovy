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
import RegisterMobileSteps
import LoginMobileSteps
import com.github.javafaker.Faker



class EndToEndMobileSteps {
	/**
	 * The step definitions below match with Katalon sample Gherkin steps
	 */
	Faker faker = new Faker();
	String nameFaker = faker.name().firstName();
	String passwordFaker = faker.internet().password();
	String emailFaker = String.format("%s@email.com", nameFaker)

	@Given("User Register successfully before apk")
	def registerAPK() {
		RegisterMobileSteps registerMobileSteps = new RegisterMobileSteps()
		registerMobileSteps.userLaunchApp()
		registerMobileSteps.userLoadedApp()
		registerMobileSteps.userClickBtnRegister("Register")
		Mobile.tap(findTestObject('Object Repository/MOBILE/record/android.widget.EditText - Alex Under, Fullname'), 30)
		Mobile.setText(findTestObject('Object Repository/MOBILE/record/android.widget.EditText - Alex Under, Fullname'), nameFaker, 30)
		Mobile.tap(findTestObject('Object Repository/MOBILE/record/android.widget.EditText - alexemail.com, Email'), 30)
		Mobile.setText(findTestObject('Object Repository/MOBILE/record/android.widget.EditText - alexemail.com, Email'),emailFaker, 30)
		Mobile.tap(findTestObject('Object Repository/MOBILE/record/android.widget.EditText - 123123123, Password'), 30)
		Mobile.setText(findTestObject('Object Repository/MOBILE/record/android.widget.EditText - 123123123, Password'), passwordFaker,30)
		Mobile.takeScreenshot('/Users/jasmine/Katalon Studio/alterra-testing/Images/screenshot-InputFormSuccess.png', FailureHandling.STOP_ON_FAILURE)
		registerMobileSteps.verifyAlert('positive')
	}

	@When("User Login alta apk")
	def loginAPK() {
		LoginMobileSteps loginMobileSteps = new LoginMobileSteps()
		loginMobileSteps.userLaunchAppForLogin()
		loginMobileSteps.userLoadedAppLogin()
		loginMobileSteps.userClickBtnLogin("Login")
		loginMobileSteps.userInputFormLogin(emailFaker, passwordFaker)
	}

	@Then("User must be showing dashboard pages")
	def verifyDashboard() {
		LoginMobileSteps loginMobileSteps = new LoginMobileSteps()
		loginMobileSteps.clickBtnLogin('positive')
		Mobile.delay(5)
		Mobile.closeApplication()
	}
}