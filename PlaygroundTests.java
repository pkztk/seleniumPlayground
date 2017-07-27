package Playground.Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Playground.Pages.Playground;

public class PlaygroundTests {

	private Playground playground;
	private WebDriver driver;
	private JavascriptExecutor js;

	@BeforeClass
	public void setUp() {
		setDriverSystemProperty();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://timvroom.com/selenium/playground/");
		playground = new Playground(driver);
		js = (JavascriptExecutor) driver;
	}

	private void setDriverSystemProperty() {
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().startsWith("windows"))
			System.setProperty("webdriver.chrome.driver", "./driver/win/chromedriver.exe");
		else if (osName.toLowerCase().startsWith("linux"))
			System.setProperty("webdriver.chrome.driver", "./driver/linux/chromedriver");
		else if (osName.toLowerCase().startsWith("mac"))
			System.setProperty("webdriver.chrome.driver", "./driver/mac/chromedriver");
	}

	@Test
	public void getPageTitle() {

		playground.chooseAnswerBox("answer1").sendKeys(driver.getTitle());
		assertEquals(driver.getTitle(), "Selenium Playground");

		// OK
	}

	@Test
	public void setAuthorName() {

		playground.setName();
		assertEquals(driver.findElement(playground.getAuthorSelector()).getAttribute("value"),
				playground.getAuthorName());

		// OK
	}

	@Test
	public void setAuthorOccupation() {

		playground.setOccupation();
		assertEquals(driver.findElement(playground.getOccupationSelector()).getAttribute("value"),
				playground.getOccupationValue());

		// OK

	}

	@Test
	public void countBlueBoxes() {

		int size = playground.countBoxes();
		playground.chooseAnswerBox("answer4").sendKeys(String.valueOf(size));
		assertEquals(String.valueOf(size), String.valueOf(js.executeScript("return boxes;")));

		// OK

	}

	@Test
	public void clickMeLink() {

		playground.clickLink("click me");

		assertEquals(js.executeScript("return expected[\'5\']").toString(), "1");

	}

	@Test
	public void findRedboxClass() {
		String boxClass = playground.findBoxClass().getAttribute("class");
		playground.chooseAnswerBox("answer6").sendKeys(boxClass);
		assertEquals(boxClass, js.executeScript("return red_class;"));

		// OK
	}

	@Test
	public void runJS_1() {
		String value = String.valueOf(js.executeScript("ran_this_js_function();return expected[\'7\'];"));
		assertEquals(value, "1");

		// OK

	}

	@Test
	public void runJS_2() {

		String value = String.valueOf(js.executeScript("return got_return_from_js_function();"));
		playground.chooseAnswerBox("answer8").sendKeys(value);

		assertEquals(playground.chooseAnswerBox("answer8").getAttribute("value"), value);

		// OK

	}

	@Test
	public void ifWroteBook() {

		WebElement element = driver.findElement(By.cssSelector("input[type=\"radio\"][value=\"wrotebook\"]"));
		element.click();

		assertEquals(element.getAttribute("checked").toString(), "true");

	}

	@Test
	public void textFromRedBox() {

		WebElement element = driver.findElement(playground.getRedBoxes());

		String letter = js.executeScript("return red_letter;").toString();
		playground.chooseAnswerBox("answer10").sendKeys(element.getText());
		assertEquals(playground.chooseAnswerBox("answer10").getAttribute("value"), "Red Box " + letter);

		// OK

	}

	@Test
	public void whichOnTop() {

		int y1 = driver.findElement(playground.getGreenBoxes()).getLocation().getY();
		int y2 = driver.findElement(playground.getOrangeBoxes()).getLocation().getY();
		String answer;

		if (y2 > y1)

			answer = "green";
		else
			answer = "orange";

		playground.chooseAnswerBox("answer11").sendKeys(answer);
		assertEquals(js.executeScript("return box_order[0];"), answer);

		// OK

	}

	@Test
	public void browserSize() {

		Dimension dimension = new Dimension(playground.getBrowserWidth(), playground.getbrowserHeight());
		driver.manage().window().setSize(dimension);

		assertEquals(driver.manage().window().getSize().toString(), dimension.toString());

		// OK

	}

	@Test
	public void isHere() {

		try {
			driver.findElement(By.id("ishere"));
			playground.chooseAnswerBox("answer13").sendKeys("yes");
		} catch (NoSuchElementException e) {
			playground.chooseAnswerBox("answer13").sendKeys("no");
		}

		assertEquals(playground.chooseAnswerBox("answer13").getAttribute("value"),
				js.executeScript("return expected[\'13\'];"));

		// OK

	}

	@Test
	public void purpleBoxVisibility() {

		if (driver.findElement(playground.getPurpleBoxes()).isDisplayed())
			playground.chooseAnswerBox("answer14").sendKeys("yes");
		else
			playground.chooseAnswerBox("answer14").sendKeys("no");

		assertEquals(playground.chooseAnswerBox("answer14").getAttribute("value"),
				js.executeScript("return expected[\'14\'];"));

		// OK

	}

	@Test
	public void clickAndWait() {

		playground.clickLink("click then wait");

		WebDriverWait wait = new WebDriverWait(driver, (long) js.executeScript("return linkwaitms;"));

		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("click after wait"))).click();
		driver.switchTo().alert().accept();

		assertTrue((long) js.executeScript("return timetoclickms;") < 500);
		assertEquals(js.executeScript("return confirmok;").toString(), "true");

		// OK
	}

	@Test
	public void submitButton() {

		driver.findElement(By.id("submitbutton")).click();

		// OK

	}

	@AfterClass
	public void checkResults() {

		js.executeScript("scroll(0, -250);");
		driver.findElement(By.id("checkresults")).click();
		driver.manage().window().maximize();

	}

}
