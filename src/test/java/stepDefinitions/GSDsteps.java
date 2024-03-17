package stepDefinitions;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.OneCognizant;
import pageObjects.beCognizant_page;
import utilities.ExcelReportGenerator;

public class GSDsteps {
	public beCognizant_page bc;
	public OneCognizant oc;
	public static WebDriver driver;
	public List<String> windowHandles;
	public static List<String> languageList = new ArrayList<String>();
	public static List<String> supportList = new ArrayList<String>();
	public static List<String> support1List = new ArrayList<String>();
	public static List<String> support2List = new ArrayList<String>();

	@Before
	public void setUp() throws IOException {
		FileReader file = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\driver.properties");
		Properties p = new Properties();
		p.load(file);

		if (p.getProperty("driver").equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		} else if (p.getProperty("driver").equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
			driver.manage().window().maximize();
		} else {
			System.out.println("WRONG BROWSER!!!!!!!!!!!!!!!!");
		}

	}

	@Given("User is on the BeCognizant page")
	public void navigateToWebsite() {

		driver.get("https://cognizantonline.sharepoint.com/sites/Be.Cognizant/SitePages/Home.aspx");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		bc = new beCognizant_page(driver);
		oc = new OneCognizant(driver);
	}

	@When("I click on profile")
	public void click_on_profile() throws InterruptedException, IOException {
		System.out.println("---Capturing user information---");
		Thread.sleep(15000);
		bc.click_profile_picture();
		Thread.sleep(3000);
		String validate = bc.getText_to_Validate();
		Assert.assertEquals(validate, "Sengupta, Swapnanil (Cognizant)");

		System.out.println("---Taking Screenshot of Profile Info---");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot,
				new File("C:\\Users\\2310300\\eclipse-workspace\\Cognizant_CAS_GSD\\ScreenShots\\Profile.png"));
		driver.navigate().refresh();
		Thread.sleep(2000);
	}

	@And("I click OneCognizant")
	public void clickOneCognizant() throws InterruptedException {
		bc.scroll_to_OneCognizant();
		Thread.sleep(2000);
		bc.click_Onecogniozant();

	}

	@Then("I switch to OneCognizant window")
	public void switchToOneCognizantWindow() {
		ArrayList<String> newTb2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newTb2.get(1));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

	}

	@When("I search GSD")
	public void searchGSD() throws InterruptedException {

		oc.Sending_GSD("GSD");
		Thread.sleep(2000);
		oc.clicking_the_search();
		Thread.sleep(2000);
		oc.clicking_the_GSD();

	}

	@Then("I switch to GSD frame")
	public void switchingFrame() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.switchTo().frame("appFrame");

	}

	@Then("I verify the title message")
	public void verifyTitleMessage() {

		String head = oc.getHeading();
		Assert.assertEquals(head, "Welcome to the all-in-one Live Support!");
		System.out.println("\n---Welcome Message Verified---");

	}

	@When("I print all the languages in GSD")
	public void printAllLanguagesInGSD() throws InterruptedException, IOException {

		oc.click_on_language_Dropdown();
		Thread.sleep(2000);
		List<WebElement> languages = oc.getAdultCount();
		for (WebElement lang : languages) {
			languageList.add(lang.getText());
			System.out.println(lang.getText());
		}
	}

	@Then("I print the support options for India GSD")
	public void printIndiaGSDSupport() throws IOException, InterruptedException {

		System.out.println("\n---All the Support Present in India's GSD---");
		String x = oc.get_All_Services();
		String[] supportarray = x.split("\n");
		System.out.println(x);
		Thread.sleep(2000);
		for (int i = 0; i < supportarray.length; i++) {
			supportList.add(supportarray[i]);
		}
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot,
				new File("C:\\Users\\2310300\\eclipse-workspace\\Cognizant_CAS_GSD\\ScreenShots\\IndiaGSd.png"));

	}

	@Then("I randomly select the first country")
	public void randomFirstCountrySelect() throws InterruptedException, IOException {
		int max = 165;
		Random random = new Random();
		int a = random.nextInt(max);

		JavascriptExecutor jse = (JavascriptExecutor) driver;

		// Randomly select first country
		oc.click_country_dropdown();
		WebElement country1 = driver.findElement(By.xpath("(//span[@class='countryFlag'])[" + a + "]"));
		jse.executeScript("arguments[0].click();", country1);
		Thread.sleep(5000);
		String country1Text = oc.getting_text_of_Country();
		Thread.sleep(3000);
		System.out.println("\n---All the Support Present in " + country1Text + "'s GSD---");
		String x = oc.get_All_Services();
		String[] supportarray = x.split("\n");
		System.out.println(x);
		for (int i = 0; i < supportarray.length; i++) {
			support1List.add(supportarray[i]);
		}
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot,
				new File("C:\\Users\\2310300\\eclipse-workspace\\Cognizant_CAS_GSD\\ScreenShots\\FirstCountry.png"));

	}

	@Then("I randomly select the second country")
	public void randomSecondCountrySelect() throws InterruptedException, IOException {

		int max = 165;
		Random random = new Random();
		int a = random.nextInt(max);

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		oc.click_country_dropdown();
		WebElement country2 = driver.findElement(By.xpath("(//span[@class='countryFlag'])[" + a + "]"));
		jse.executeScript("arguments[0].click();", country2);
		Thread.sleep(5000);
		String country2Text = oc.getting_text_of_Country();
		Thread.sleep(3000);
		System.out.println("\n---All the Support Present in " + country2Text + "'s GSD---");
		WebElement help1 = driver.findElement(By.xpath("//div[@class='d-flex flex-row titles-row']"));
		String x = help1.getText();
		String[] supportarray = x.split("\n");
		System.out.println(x);
		for (int i = 0; i < supportarray.length; i++) {
			support2List.add(supportarray[i]);
		}
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot,
				new File("C:\\Users\\2310300\\eclipse-workspace\\Cognizant_CAS_GSD\\ScreenShots\\SecondCountry.png"));

		ExcelReportGenerator excelReportGenerator = new ExcelReportGenerator();

		excelReportGenerator.generateExcelReport(languageList, supportList, support1List, support2List);

	}

	@After
	public void tearDown() {
		driver.quit();

	}

}
