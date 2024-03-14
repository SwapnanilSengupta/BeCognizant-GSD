package pageObjects;

import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OneCognizant extends BasePage {

	public OneCognizant(WebDriver driver) {
		super(driver);
	}

	// Elements
	@FindBy(xpath = "//input[@id='oneC_searchAutoComplete']")
	WebElement search_input;

	@FindBy(xpath = "//div[@class='searchInputIcon']")
	WebElement search_button;

	@FindBy(xpath = "//div[@class='appsResultText']")
	WebElement GSD;

	@FindBy(xpath = "//p[@class='Welcome-automated-text']")
	WebElement heading;

	@FindBy(xpath = "//form[@class='d-flex ms-auto']//span[@class='optionLangSelected'][normalize-space()='English']")
	WebElement langDropDown;

	@FindBy(xpath = "//ul[@class='dropdown-menu langList show']//li[@role='presentation']//a")
	List<WebElement> LanguageCount;

	@FindBy(xpath = "//div[@class='d-flex flex-row titles-row']")
	WebElement services;

	@FindBy(xpath = "//button[@id='menu4']")
	WebElement Country;


	@FindBy(xpath = "//form[@class='d-flex ms-auto']//a[@tabindex='0']//span[@class='optionCountrySelected']")
	WebElement country_tag;

	// methods
	public void Sending_GSD(String input) {
		search_input.sendKeys(input);
	}

	public void clicking_the_search() {
		search_button.click();
	}

	public void clicking_the_GSD() {
		GSD.click();
	}

	public String getHeading() {
		String text = heading.getText();
		return text;
	}

	public void click_on_language_Dropdown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", langDropDown);
	}

	public List<WebElement> getAdultCount() {
		return LanguageCount;
	}

	public String get_All_Services() {
		String x = services.getText();
		return x;
	}

	public void click_country_dropdown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", Country);
	}

	public String getting_text_of_Country() {
		String ctr = country_tag.getText();
		return ctr;
	}

}
