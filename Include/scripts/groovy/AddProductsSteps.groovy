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



class AddProductsSteps {
	/**
	 * The step definitions below match with Katalon sample Gherkin steps
	 */
	@Given("User login before")
	public void user_login_before() {
		WebUI.callTestCase(findTestCase('WEB/TC002 - LoginSuccess'), [:], FailureHandling.STOP_ON_FAILURE)
	}

	@When("User click button beli on dashboard alta shop")
	public void user_click_button_beli_on_dashboard_alta_shop() {
		WebUI.delay(3)
		WebUI.verifyElementPresent(findTestObject('Object Repository/WEB/OR011 - BtnBeliProduct'), 30)
		WebUI.click(findTestObject('Object Repository/WEB/OR011 - BtnBeliProduct'))
		WebUI.takeScreenshot()
	}

	@And("User verify badge add to cart")
	public void user_verify_badge_add_to_cart() {
		WebUI.delay(3)
		def getBadge = WebUI.getText(findTestObject('Object Repository/WEB/OR012 - ElementBadgeCart'))
		WebUI.comment(getBadge)
		if(getBadge == '1') {
			WebUI.verifyMatch(getBadge, "1", false)
		}
		else {
			WebUI.verifyMatch(getBadge, "2", false)
		}
		WebUI.verifyElementPresent(findTestObject('Object Repository/WEB/OR012 - ElementBadgeCart'), 30)
		WebUI.takeScreenshot()
	}

	@And("User Click icon cart")
	public void user_Click_icon_cart() {
		WebUI.click(findTestObject('Object Repository/WEB/OR013 - ButtonCart'))
		WebUI.delay(3)
		WebUI.takeScreenshot()
	}

	@Then("User has been loaded page order and success input item to cart order")
	public void user_has_been_loaded_page_order_and_success_input_item_to_cart_order() {
		WebUI.verifyElementPresent(findTestObject('Object Repository/WEB/OR014 - CartTextOrder'), 30)
		WebUI.takeScreenshot()
	}

	@When("User click button beli 2 item on dashboard alta shop")
	public void verifyBadgeCartMoreThanOne() {
		user_click_button_beli_on_dashboard_alta_shop()
		WebUI.verifyElementPresent(findTestObject('Object Repository/WEB/OR017 - ButtonBeliProductItem2'), 30)
		WebUI.click(findTestObject('Object Repository/WEB/OR017 - ButtonBeliProductItem2'))
		WebUI.takeScreenshot()
	}

	@Then("user has been successfully add two items")
	public void user_has_been_successfully_add_two_items() {
		WebUI.verifyElementPresent(findTestObject('Object Repository/WEB/OR014 - CartTextOrder'), 30)
		WebUI.verifyElementPresent(findTestObject('Object Repository/WEB/OR021 - TotalQty'), 30)
		WebUI.takeScreenshot()
	}

	@When("User click button details")
	public void user_click_button_details() {
		// Write code here that turns the phrase above into concrete actions
		WebUI.click(findTestObject('Object Repository/WEB/OR018 - BtnDetailsProduct'))
		WebUI.takeScreenshot()
	}

	@Then("User Click ratings and success")
	public void user_Click_ratings_and_success() {
		// Write code here that turns the phrase above into concrete actions
		WebUI.click(findTestObject('Object Repository/WEB/OR022 - AddRatings'))
		WebUI.takeScreenshot()
		WebUI.delay(5)
		WebUI.takeScreenshot()
	}

	@When("User click menu categories")
	public void user_click_menu_categories() {
		WebUI.click(findTestObject('Object Repository/WEB/OR025 - SelectCategory'))
		WebUI.delay(5)
		WebUI.takeScreenshot()
	}

	@And("User choose items categories")
	public void user_choose_items_categories() {
		WebUI.click(findTestObject('Object Repository/WEB/OR024 - ChooseCategoryItems'))
	}

	@Then("User check product is in categories or not")
	public void user_check_product_is_in_categories_or_not() {
		def getContent = WebUI.getText(findTestObject('Object Repository/WEB/OR027 - ContentAlertAfterChooseCategories'))
		println getContent
		WebUI.verifyElementPresent(findTestObject('Object Repository/WEB/OR027 - ContentAlertAfterChooseCategories'), 30)
	}

	@Then("User verify title and price products")
	public void verifyProductsDetails() {
		WebUI.verifyElementPresent(findTestObject('Object Repository/WEB/OR019 - VerifyTitle'), 30)
		WebUI.verifyElementPresent(findTestObject('Object Repository/WEB/OR020 - VerifyPrice'), 30)
		def getTitle = WebUI.getText(findTestObject('Object Repository/WEB/OR019 - VerifyTitle'))
		def getPrice = WebUI.getText(findTestObject('Object Repository/WEB/OR020 - VerifyPrice'))
		WebUI.comment(getTitle)
		WebUI.comment(getPrice)
	}
}