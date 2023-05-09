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
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration



class LoginMobileSteps {
	Faker faker = new Faker();
	String nameFaker = faker.name().firstName();
	String passwordFaker = faker.internet().password();


	@Given("User launch app login")
	public void userLaunchAppForLogin() {
		String directory = RunConfiguration.getProjectDir()
		def pathApk = ((directory + '/apk/') + 'app-release-new.apk')
		Mobile.startApplication(pathApk, true)
	}

	@When("User has been loaded app login")
	public void userLoadedAppLogin() {
		Mobile.verifyElementVisible(findTestObject('Object Repository/MOBILE/record/android.widget.Button'), 30)
		Mobile.takeScreenshot('/Users/jasmine/Katalon Studio/alterra-testing/Images/screenshot-pageloaded.png', FailureHandling.STOP_ON_FAILURE)
	}

	@And("User click button icon login and define (.*)")
	public void userClickBtnLogin(String description) {
		Mobile.comment(description)
		Mobile.tap(findTestObject('Object Repository/Login/android.widget.Button'), 30)
		Mobile.takeScreenshot('/Users/jasmine/Katalon Studio/alterra-testing/Images/screenshot-pagelogin.png', FailureHandling.STOP_ON_FAILURE)
	}

	@And("User fill form login on apk (.*) and (.*)")
	public void userInputFormLogin(String email, String password) {
		Mobile.tap(findTestObject('Object Repository/Login/android.widget.EditText - Email'), 30)
		Mobile.setText(findTestObject('Object Repository/Login/android.widget.EditText - Email'), email, 30)
		Mobile.tap(findTestObject('Object Repository/Login/android.widget.EditText - Password'), 30)
		Mobile.setText(findTestObject('Object Repository/Login/android.widget.EditText - Password'), password, 30)

		Mobile.takeScreenshot('/Users/jasmine/Katalon Studio/alterra-testing/Images/screenshot-InputFormSuccess.png', FailureHandling.STOP_ON_FAILURE)
	}

	@Then("User click button login on apk and verify based on (.*)")
	public void clickBtnLogin(String status) {
		if(status == 'positive') {
			Mobile.tap(findTestObject('Object Repository/Login/android.widget.Button (1)'), 30, FailureHandling.STOP_ON_FAILURE)
			Mobile.verifyElementVisible(findTestObject('Object Repository/MOBILE/record/android.widget.Button'), 30)
			Mobile.delay(5)
			Mobile.takeScreenshot()
			Mobile.tap(findTestObject('Object Repository/MOBILE/android.widget.ButtonBeli (1)'), 30, FailureHandling.STOP_ON_FAILURE)
			Mobile.delay(5)
			Mobile.takeScreenshot()
			Mobile.tap(findTestObject('Object Repository/MOBILE/record beli/android.widget.Button'), 30)
			Mobile.delay(5)
			def getCart = Mobile.getAttribute(findTestObject('Object Repository/MOBILE/android.widget.ButtonBeli (1)'), 'text', 0)
			Mobile.comment(getCart)
			Mobile.tap(findTestObject('Object Repository/MOBILE/record beli/android.widget.Button'), 30)
		}
		else {
			Mobile.tap(findTestObject('Object Repository/Login/android.widget.Button (1)'), 30, FailureHandling.STOP_ON_FAILURE)
			Mobile.verifyElementVisible(findTestObject('Object Repository/Login/android.widget.Button (1)'), 30)
			Mobile.delay(5)
			Mobile.takeScreenshot()
		}
	}
}