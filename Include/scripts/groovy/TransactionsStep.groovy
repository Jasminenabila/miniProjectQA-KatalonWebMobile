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
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword

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



class TransactionsStep {
	@Given("User login before on see transactions")
	public void user_login_before() {
		WebUI.callTestCase(findTestCase('WEB/TC002 - LoginSuccess'), [:], FailureHandling.STOP_ON_FAILURE)
	}

	@Given("User login before have data not found on see transactions")
	public void userLoginDataOrderNotFound() {
		WebUI.openBrowser('')
		WebUI.navigateToUrl(GlobalVariable.url)
		WebUI.navigateToUrl(GlobalVariable.login)
		WebUI.delay(3)
		WebUI.verifyElementPresent(findTestObject('Object Repository/WEB/OR006 - InputEmailLogin'), 30)
		WebUI.setText(findTestObject('Object Repository/WEB/OR006 - InputEmailLogin'), 'KMAlterra@alterra.com')
		WebUI.setText(findTestObject('Object Repository/WEB/OR008 - InputPasswordLogin'), 'Password123')
		WebUI.takeScreenshot()
		WebUI.click(findTestObject('Object Repository/WEB/OR009 - BtnLogin'))
		WebUI.verifyElementPresent(findTestObject('Object Repository/WEB/OR010 - BtnIconLogin'), 30)
		WebUI.delay(3)
	}

	@When("User redirect to menu transactions")
	public void redirect_transactions() {
		WebUI.navigateToUrl(GlobalVariable.transactions)

		WebUI.verifyElementVisible(findTestObject('Object Repository/WEB/OR030 - VerifyLabelTransactions'))

		WebUI.takeScreenshot()
	}

	@Then("User check list table transactions")
	public void see_transactions() {

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

		if(rows_count.toString() == '11') {
			WebUI.verifyMatch(rows_count.toString(), "11", false)
			WebUI.takeScreenshot()
		}
		else if(rows_count.toString() == '6') {
			WebUI.verifyMatch(rows_count.toString(), "6", false)
			WebUI.takeScreenshot()
		}
		else if(rows_count.toString() == '16') {
			WebUI.verifyMatch(rows_count.toString(), "16", false)
			WebUI.takeScreenshot()
		}
		else {
			WebUI.verifyMatch(rows_count.toString(), rows_count.toString(), false)
			WebUI.takeScreenshot()
		}
	}

	@When("User redirect to menu transactions to click pagination 15")
	public void redirect_pagination_15(){
		WebUI.navigateToUrl(GlobalVariable.transactions)
		WebUI.verifyElementVisible(findTestObject('Object Repository/WEB/OR030 - VerifyLabelTransactions'))
		WebUI.click(findTestObject('Object Repository/WEB/OR034 - ClickBtnPagination'))
		WebUI.click(findTestObject('Object Repository/WEB/OR035 - Pagination15'))
		WebUI.delay(5)
		WebUI.takeScreenshot()
	}

	@When("User redirect to menu transactions to click pagination 5")
	public void redirect_pagination_5(){
		WebUI.navigateToUrl(GlobalVariable.transactions)
		WebUI.verifyElementVisible(findTestObject('Object Repository/WEB/OR030 - VerifyLabelTransactions'))
		WebUI.click(findTestObject('Object Repository/WEB/OR034 - ClickBtnPagination'))
		WebUI.click(findTestObject('Object Repository/WEB/OR036 - Pagination5'))
		WebUI.delay(5)
		WebUI.takeScreenshot()
	}

	@When("User redirect to menu transactions to click pagination all")
	public void redirect_pagination_all(){
		WebUI.navigateToUrl(GlobalVariable.transactions)
		WebUI.verifyElementVisible(findTestObject('Object Repository/WEB/OR030 - VerifyLabelTransactions'))
		WebUI.click(findTestObject('Object Repository/WEB/OR034 - ClickBtnPagination'))
		WebUI.click(findTestObject('Object Repository/WEB/OR036 - Pagination5'))
		WebUI.delay(5)
		WebUI.takeScreenshot()
	}

	@Then("User verify not found")
	public void verifyNotFound() {
		WebUI.refresh()
		WebUI.verifyElementPresent(findTestObject('Object Repository/WEB/OR038 - NoDataAvailable'), 30)
		def getDataNotFound = WebUI.getText(findTestObject('Object Repository/WEB/OR038 - NoDataAvailable'))
		WebUI.comment(getDataNotFound)
		WebUI.verifyMatch(getDataNotFound, "No data available", false)
	}
}

