package Playground.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Playground {

	private WebDriver driver;

	private By authorSelector = By.name("name");
	private String authorName = "Kilgore Trout";

	private By occupationSelector = By.name("occupation");
	private String occupationName = "Science Fiction Author";
	private String occupationValue = "scifiauthor";

	private By blueBoxes = By.className("bluebox");
	private By orangeBoxes = By.id("orangebox");
	private By redBoxes = By.id("redbox");
	private By greenBoxes = By.id("greenbox");
	private By purpleBoxes = By.id("purplebox");

	private int browserWidth = 850;
	private int browserHeight = 650;

	public Playground(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement chooseAnswerBox(String name) {
		return driver.findElement(By.id(name));
	}

	public WebElement findBoxClass() // for red box
	{
		return driver.findElement(redBoxes);
	}

	public void setName() {
		driver.findElement(authorSelector).sendKeys(authorName);
	}

	public void setOccupation() {
		Select dropdown = new Select(driver.findElement(occupationSelector));
		dropdown.selectByVisibleText(occupationName);
	}

	public int countBoxes() {
		List<WebElement> boxes = driver.findElements(blueBoxes);
		return boxes.size();
	}

	public void wroteBook(String answer) {

		if (answer == "Yes")
			driver.findElement(By.cssSelector("input[type=\"radio\"][value=\"wrotebook\"]")).click();

		if (answer == "No")
			driver.findElement(By.cssSelector("input[type=\"radio\"][value=\"didntwritebook\"]")).click();
	}

	public void clickLink(String link_name) {

		driver.findElement(By.linkText(link_name)).click();

	}

	public By getAuthorSelector() {
		return authorSelector;
	}

	public void setAuthorSelector(By authorSelector) {
		this.authorSelector = authorSelector;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public By getOccupationSelector() {
		return occupationSelector;
	}

	public void setOccupationSelector(By occupationSelector) {
		this.occupationSelector = occupationSelector;
	}

	public String getOccupationName() {
		return occupationName;
	}

	public void setOccupationName(String occupationName) {
		this.occupationName = occupationName;
	}

	public String getOccupationValue() {
		return occupationValue;
	}

	public void setOccupation_value(String occupationValue) {
		this.occupationValue = occupationValue;
	}

	public By getBlueBoxes() {
		return blueBoxes;
	}

	public void setBlueBoxes(By blueBoxes) {
		this.blueBoxes = blueBoxes;
	}

	public By getOrangeBoxes() {
		return orangeBoxes;
	}

	public void setOrangeBoxes(By orangeBoxes) {
		this.orangeBoxes = orangeBoxes;
	}

	public By getRedBoxes() {
		return redBoxes;
	}

	public void setRed_boxes(By red_boxes) {
		this.redBoxes = red_boxes;
	}

	public By getGreenBoxes() {
		return greenBoxes;
	}

	public void setGreenBoxes(By greenBoxes) {
		this.greenBoxes = greenBoxes;
	}

	public By getPurpleBoxes() {
		return purpleBoxes;
	}

	public void setPurpleBoxes(By purpleBoxes) {
		this.purpleBoxes = purpleBoxes;
	}

	public int getBrowserWidth() {
		return browserWidth;
	}

	public void setBrowserWidth(int browserWidth) {
		this.browserWidth = browserWidth;
	}

	public int getbrowserHeight() {
		return browserHeight;
	}

	public void setbrowserHeight(int browserHeight) {
		this.browserHeight = browserHeight;
	}

}
