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




class OrdersSteps {
	@Given("User login before on order page")
	public void user_login_before() {
		WebUI.callTestCase(findTestCase('WEB/TC002 - LoginSuccess'), [:], FailureHandling.STOP_ON_FAILURE)
	}

	@When("User click button beli on dashboard alta shop orders scenario")
	public void user_click_button_beli_on_dashboard_alta_shop() {
		WebUI.delay(3)
		WebUI.verifyElementPresent(findTestObject('Object Repository/WEB/OR011 - BtnBeliProduct'), 30)
		WebUI.click(findTestObject('Object Repository/WEB/OR011 - BtnBeliProduct'))
		WebUI.takeScreenshot()
	}

	@And("User verify badge add to cart on order page")
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

	@And("User Click icon cart on order page and add qty")
	public void user_Click_icon_cart() {
		WebUI.click(findTestObject('Object Repository/WEB/OR013 - ButtonCart'))
		WebUI.delay(3)
		WebUI.takeScreenshot()
		WebUI.click(findTestObject('Object Repository/WEB/OR026 - AddQty'))
		def getQty = WebUI.getText(findTestObject('Object Repository/WEB/OR021 - TotalQty'))
		WebUI.verifyMatch(getQty, "2", false)
		WebUI.delay(3)
		WebUI.takeScreenshot()
	}

	@Then("User click button bayar and redirect list transactions")
	public void clickBtnBayar() {
		WebUI.click(findTestObject('Object Repository/WEB/OR029 - BtnBayar'))
		WebUI.delay(3)
		WebUI.takeScreenshot()

		WebUI.verifyElementVisible(findTestObject('Object Repository/WEB/OR030 - VerifyLabelTransactions'))

		WebDriver driver = DriverFactory.getWebDriver()


		WebElement myTable = driver.findElement(By.cssSelector('div.v-application.v-application--is-ltr.theme--light:nth-child(2) div.v-application--wrap main.v-main div.v-main__wrap div.container div.v-card.v-sheet.theme--light:nth-child(2) div.v-data-table.v-data-table--has-bottom.theme--light div.v-data-table__wrapper > table:nth-child(1)'))

		List<WebElement> rows_in_table = myTable.findElements(By.tagName('tr'))

		int rows_count = rows_in_table.size()
		println rows_count

		Loop: for (int row = 0; row < rows_count; row++) {

			List<WebElement> columns_in_row = rows_in_table.get(row).findElements(By.tagName('td'))

			int columns_count = columns_in_row.size()

			for (int column = 0; column < columns_count; column++) {

				String celltext = columns_in_row.get(column).getText()
				WebUI.comment(celltext)

				Loop: break
			}
		}

		WebUI.takeScreenshot()
	}

	@And("User Click icon cart on order page and minus qty")
	public void userCartMinusqty() {
		WebUI.click(findTestObject('Object Repository/WEB/OR013 - ButtonCart'))
		WebUI.delay(3)
		WebUI.takeScreenshot()
		WebUI.click(findTestObject('Object Repository/WEB/OR031 - MinusQty'))
		WebUI.delay(3)
		WebUI.takeScreenshot()
	}

	@Then("User verify orders is empty")
	public void verifyOrdersEmpty() {
		WebUI.verifyElementPresent(findTestObject('Object Repository/WEB/OR032 - OrderIsEmpty'), 30)
		def getOrdersEmpty = WebUI.getText(findTestObject('Object Repository/WEB/OR032 - OrderIsEmpty'))
		WebUI.delay(3)
		WebUI.takeScreenshot()
	}

	@Given("User open dashboard alta shop")
	public void openDashboard() {
		WebUI.openBrowser('')
		WebUI.navigateToUrl(GlobalVariable.url)
		WebUI.verifyElementPresent(findTestObject('Object Repository/WEB/OR001 - VerifyAltaShopWeb'), 30)
		WebUI.takeScreenshot()
	}

	@Then("User must be redirect to login page")
	public void redirectLogin() {
		WebUI.click(findTestObject('Object Repository/WEB/OR029 - BtnBayar'))
		WebUI.delay(3)
		WebUI.verifyElementPresent(findTestObject('Object Repository/WEB/OR033 - VerifyLabelTextLoginPage'), 30)
		WebUI.takeScreenshot()
	}

	@Then("User must be redirect to orders empty")
	public void mustRedirectOrdersEmpty() {
		def getOrdersEmpty = WebUI.getText(findTestObject('Object Repository/WEB/OR032 - OrderIsEmpty'))
		WebUI.verifyMatch(getOrdersEmpty, "Order is empty!", false)
		WebUI.takeScreenshot()
		WebUI.closeBrowser()
	}
}
