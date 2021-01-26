import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI


List<String> page1 = new ArrayList<>()
List<String> page2 = new ArrayList<>()
List<String> page3 = new ArrayList<>()
List<String> page4 = new ArrayList<>()
List<String> page5 = new ArrayList<>()
List<String> page6 = new ArrayList<>()

List<ArrayList> lArray = new ArrayList<ArrayList>();//Create a list of lists
lArray.add(page1)
lArray.add(page2)
lArray.add(page3)
lArray.add(page4)
lArray.add(page5)
lArray.add(page6)

myObject = new TestObject("myObject")
//Loop paginate
pagi = 1 //paginate start from 1
index = 0
//list for paginate buttons
List<String> list2 = new ArrayList<>()
WebUI.openBrowser('')
WebUI.navigateToUrl('http://172.31.1.104:9000/Login.aspx')
//WebUI.navigateToUrl('https://datatables.net/examples/data_sources/dom')
WebDriver driver2 = DriverFactory.getWebDriver()
'To locate paginate buttons'
list2 = driver2.findElements(By.xpath("//a[contains(@class, 'paginate_button')]"))
println list2.size()
count = list2.size() - 2 //remove previous and next from the count
WebUI.closeBrowser()

//loop paginate
for (int i = 0; i < count; i++){
	
	WebUI.openBrowser('')
	WebUI.navigateToUrl('https://datatables.net/examples/data_sources/dom')

	//modify paginate object xpath property
	path = "//div[@id='example_paginate']/span/a["+pagi+"]"
	myObject.addProperty("xpath", ConditionType.EQUALS, path, true)
	WebUI.click(myObject)//use new relative xpath locator
	WebUI.delay(1)


	//get all table cells data to the list array
	WebDriver driver = DriverFactory.getWebDriver()
	'To locate table'
	WebElement Table = driver.findElement(By.xpath('//table/tbody'))
	'To locate rows of table it will Capture all the rows available in the table'
	List<Double> rows_table = Table.findElements(By.tagName('tr'))
	int rows_count = rows_table.size()

	'Loop will execute for all the rows of the table'
	for (int row = 0; row < rows_count; row++) {
		'To locate columns(cells) of that specific row'
		List<Double> Columns_row = rows_table.get(row).findElements(By.tagName('td'))

		'To calculate no of columns(cells) In that specific row'
		int columns_count = Columns_row.size()

		'Loop will execute till the last cell of that specific row'
		for (int column = 0; column < columns_count; column++) {
			'It will retrieve text from each cell'
			String celltext = Columns_row.get(column).getText()
			lArray.get(index).add(celltext)//add page data to array(pagex) which is in array lArray
		}
	}
	pagi++
	index++
	WebUI.delay(20)
	WebUI.closeBrowser()
	WebUI.delay(20)
}
println page1
println page2
println page3
println page4
println page5
println page6

//check that page1 contains expected value
boolean ans = page1.contains("Ashton Cox");
if (ans)
	System.out.println("The list contains expected name");
else
	System.out.println("The list does not contains expected name");