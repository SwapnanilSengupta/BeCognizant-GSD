package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class beCognizant_page extends BasePage {

	public beCognizant_page(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//img[contains(@alt,'SS')]")
	WebElement profile_pic;

	@FindBy(xpath = "//div[@id='mectrl_currentAccount_primary']")
	WebElement ValidateName;

	@FindBy(xpath = "//div[text()='OneCognizant']")
	WebElement oneCognizant;

	public void click_profile_picture() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", profile_pic);

	}

	public String getText_to_Validate() {
		String validate = ValidateName.getText();
		return validate;

	}

	public void scroll_to_OneCognizant() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", oneCognizant);

	}

	public void click_Onecogniozant() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(oneCognizant)).click();

	}

}