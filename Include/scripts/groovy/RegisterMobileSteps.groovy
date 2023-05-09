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
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import io.appium.java_client.AppiumDriver
import com.github.javafaker.Faker



class RegisterMobileSteps {

	Faker faker = new Faker();
	String nameFaker = faker.name().firstName();
	String passwordFaker = faker.internet().password();


	@Given("User launch app")
	public void userLaunchApp() {
		String directory = RunConfiguration.getProjectDir()
		def pathApk = ((directory + '/apk/') + 'app-release-new.apk')
		Mobile.startApplication(pathApk, true)
	}

	@When("User has been loaded app")
	public void userLoadedApp() {
		Mobile.verifyElementVisible(findTestObject('Object Repository/MOBILE/record/android.widget.Button'), 30)
		Mobile.delay(3)
		Mobile.takeScreenshot('/Users/jasmine/Katalon Studio/alterra-testing/Images/screenshot-pageloaded.png', FailureHandling.STOP_ON_FAILURE)
	}

	@And("User click button icon login and click hyperlink register and define (.*)")
	public void userClickBtnRegister(String description) {
		Mobile.comment(description)
		Mobile.tap(findTestObject('Object Repository/MOBILE/record/android.widget.Button'), 30)
		Mobile.delay(3)
		Mobile.tap(findTestObject('Object Repository/MOBILE/record/android.widget.Button (1)'), 30)
		Mobile.delay(3)
		Mobile.takeScreenshot('/Users/jasmine/Katalon Studio/alterra-testing/Images/screenshot-pageregister.png', FailureHandling.STOP_ON_FAILURE)
	}

	@And("User fill form register on apk alta shop (.*),(.*) and (.*) define (.*)")
	public void userInputForm(String fullname, String email, String password, String status) {
		if(status == 'positive') {
			Mobile.tap(findTestObject('Object Repository/MOBILE/record/android.widget.EditText - Alex Under, Fullname'), 30)
			Mobile.setText(findTestObject('Object Repository/MOBILE/record/android.widget.EditText - Alex Under, Fullname'), fullname+" "+nameFaker, 30)

			Mobile.tap(findTestObject('Object Repository/MOBILE/record/android.widget.EditText - alexemail.com, Email'), 30)
			Mobile.setText(findTestObject('Object Repository/MOBILE/record/android.widget.EditText - alexemail.com, Email'), email+" "+nameFaker+"@alterra.com", 30)

			Mobile.tap(findTestObject('Object Repository/MOBILE/record/android.widget.EditText - 123123123, Password'), 30)
			Mobile.setText(findTestObject('Object Repository/MOBILE/record/android.widget.EditText - 123123123, Password'), password+" "+passwordFaker,30)
			Mobile.takeScreenshot('/Users/jasmine/Katalon Studio/alterra-testing/Images/screenshot-InputFormSuccess.png', FailureHandling.STOP_ON_FAILURE)
		}
		else {
			Mobile.tap(findTestObject('Object Repository/MOBILE/record/android.widget.EditText - Alex Under, Fullname'), 30)
			Mobile.setText(findTestObject('Object Repository/MOBILE/record/android.widget.EditText - Alex Under, Fullname'), fullname, 30)

			Mobile.tap(findTestObject('Object Repository/MOBILE/record/android.widget.EditText - alexemail.com, Email'), 30)
			Mobile.setText(findTestObject('Object Repository/MOBILE/record/android.widget.EditText - alexemail.com, Email'), email, 30)

			Mobile.tap(findTestObject('Object Repository/MOBILE/record/android.widget.EditText - 123123123, Password'), 30)
			Mobile.setText(findTestObject('Object Repository/MOBILE/record/android.widget.EditText - 123123123, Password'), password,30)
			Mobile.takeScreenshot('/Users/jasmine/Katalon Studio/alterra-testing/Images/screenshot-InputFormFailed.png', FailureHandling.STOP_ON_FAILURE)
		}
	}

	@Then("User click button register on apk and verify based on (.*)")
	public void verifyAlert(String status) {
		Mobile.tap((findTestObject('Object Repository/MOBILE/record/android.widget.Button (2)')), 30)
		if(status == 'positive') {
			Mobile.verifyElementVisible(findTestObject('Object Repository/MOBILE/record/android.widget.Button (3)'), 30)
			Mobile.delay(3)
			Mobile.takeScreenshot('/Users/jasmine/Katalon Studio/alterra-testing/Images/screenshot-redirectPage.png', FailureHandling.STOP_ON_FAILURE)
		}
		else {
			Mobile.verifyElementVisible(findTestObject('Object Repository/MOBILE/record/android.widget.Button (2)'), 30)
			Mobile.delay(3)
			Mobile.takeScreenshot('/Users/jasmine/Katalon Studio/alterra-testing/Images/screenshot-registerError.png', FailureHandling.STOP_ON_FAILURE)
		}
	}
}