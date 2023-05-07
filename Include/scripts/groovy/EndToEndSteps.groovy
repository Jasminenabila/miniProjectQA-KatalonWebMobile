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
import RegisterSteps
import LoginSteps
import AddProductsSteps
import OrdersSteps
import TransactionsStep
import com.github.javafaker.Faker




class EndToEndSteps {

	Faker faker = new Faker();
	String nameFaker = faker.name().firstName();
	String passwordFaker = faker.internet().password();
	String emailFaker = String.format("%s@email.com", nameFaker)

	@Given("User Register successfully before")
	public void userRegister() {
		RegisterSteps registerSteps = new RegisterSteps()
		registerSteps.user_open_website_alterra()
		registerSteps.goToRegisterPage("Register Success")
		WebUI.setText(findTestObject('Object Repository/WEB/OR002 - InputFullname'), nameFaker)
		WebUI.setText(findTestObject('Object Repository/WEB/OR003 - InputEmail'), emailFaker)
		WebUI.setText(findTestObject('Object Repository/WEB/OR004 - InputPassword'), passwordFaker)
		WebUI.click(findTestObject('Object Repository/WEB/OR005 - BtnRegister'))
		WebUI.verifyElementPresent(findTestObject('Object Repository/WEB/OR006 - InputEmailLogin'), 30)
		WebUI.delay(2)
		WebUI.takeScreenshot()
	}

	@When("User Login alta shop")
	public void userLogin() {
		WebUI.navigateToUrl(GlobalVariable.login)
		WebUI.refresh()
		LoginSteps loginSteps = new LoginSteps()
		loginSteps.user_input_email_and_password(emailFaker, passwordFaker)
		loginSteps.user_click_button_login_and_has_been_login_success()
	}

	@And("User add products to cart until click button bayar")
	public void addProdCart() {
		AddProductsSteps addProducsStep = new AddProductsSteps()
		addProducsStep.user_click_button_beli_on_dashboard_alta_shop()
		addProducsStep.user_Click_icon_cart()
		OrdersSteps orderSteps = new OrdersSteps()
		orderSteps.clickBtnBayar()
	}

	@Then("User must be showing list transactions")
	public void seeTransaction() {
		WebUI.refresh()
		TransactionsStep transactionSteps = new TransactionsStep()
		transactionSteps.see_transactions()
		WebUI.refresh()
		WebUI.back()
		WebUI.refresh()
		WebUI.takeScreenshot()
		WebUI.closeBrowser()
	}
}