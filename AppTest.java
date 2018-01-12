package com.comeon.automationWorld;

import com.comeon.automationWorld.WebDriverAPI.WebDriverInstance;
import com.comeon.automationWorld.WebDriverFactory.WebDriverFactory;
import com.comeon.automationWorld.casinoApi.factories.CasinoFactory;
import com.comeon.automationWorld.casinoApi.interfaces.ICasinoApi;
import com.comeon.automationWorld.dataObjects.Results;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase

{
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		assertTrue(true);
	}

	/**
	 * This test tests that you are able to navigate on a desktop to the game
	 * Winterberries and click on the start button throw the two methods
	 * ClickByImage
	 * 
	 * author: Martin Pålman
	 * 
	 * @throws Exception
	 */
	@org.junit.Test
	public void testCasinoDesktop() throws Exception {
		String site = "https://comeon.com";

		File directory = new File(".");
		File[] subdirs = directory.listFiles((FileFilter) DirectoryFileFilter.DIRECTORY);
		for (File dir : subdirs) {
			System.out.println("Directory: " + dir.getName());
		}

		Results r = new Results();

		WebDriverFactory factory = new WebDriverFactory();
		WebDriverInstance driverInstance = factory.getDriver("CHROME");

		WebDriver activeDriver = driverInstance.getWebdriver();
		activeDriver.get(site);

		// Click CasinoButton
		WebDriverWait wait = new WebDriverWait(activeDriver, 500);
		WebElement casinoButton = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[8]/div[3]/nav/a[3]/span[2]")));
		if (casinoButton.isDisplayed()) {
			casinoButton.click();
			r.setStatus("PASSED");
		} else {
			r.setStatus("FAILED");
			r.setMessage("MSG: Unable to locate button - ");
			System.out.println(r.getMessage() + r.getStatus());
		}
		System.out.println("Clicked CasinoButton: " + r.getStatus());

		// Type in the searchField
		Thread.sleep(1000);
		WebElement searchField = activeDriver.findElement(By.cssSelector("#casino-gamesearch"));
		Thread.sleep(100);
		if (searchField.isDisplayed()) {
			searchField.sendKeys("winterberries");
			r.setStatus("PASSED");
		} else {
			r.setStatus("FAILED");
			r.setMessage("MSG: Unable to locate search field - ");
		}
		System.out.println("Clicked SearchField: " + r.getStatus());

		// Click the typed game
		WebElement clickGame = activeDriver
				.findElement(By.cssSelector("#searchresults > div > div.cnt-game-image.list > div > section > h1 > a"));
		Thread.sleep(3000);
		clickGame.click();

		// ClickByImage
		CasinoFactory Casifactory = new CasinoFactory("DESKTOP", driverInstance, "winterberries/start.PNG");
		ICasinoApi casino = Casifactory.getImplementation();

		r = casino.clickByImage();
		System.out.println("Clicked Image:  " + r.getStatus());
		System.out.println("Test Message: " + r.getMessage());

		CasinoFactory Casifactory2 = new CasinoFactory("DESKTOP", driverInstance, "winterberries/betStake.PNG");
		ICasinoApi casino2 = Casifactory2.getImplementation();

		r = casino2.clickByImage();
		System.out.println("Clicked Image:  " + r.getStatus());
		System.out.println("Test Message: " + r.getMessage());

		CasinoFactory Casifactory3 = new CasinoFactory("DESKTOP", driverInstance, "winterberries/play.PNG");
		ICasinoApi casino3 = Casifactory3.getImplementation();

		r = casino3.clickByImage();
		System.out.println("Clicked Image:  " + r.getStatus());
		System.out.println("Test Message: " + r.getMessage());

	}

	/**
	 * This test tests that you are able to navigate on an android to the game
	 * Winterberries and click on the start button throw the two methods
	 * ClickByImage
	 * 
	 * author: Martin Pålman
	 * 
	 * @throws Exception
	 */
	@org.junit.Test
	public void testCasinoMobile() throws Exception {
		WebDriver activeDriver = null;
		Results r = new Results();

		DesiredCapabilities capabilities = DesiredCapabilities.android();
		ChromeOptions options = new ChromeOptions();

		capabilities.setCapability(MobileCapabilityType.PLATFORM, "Android");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.0");
		capabilities.setCapability("deviceName", "ce0916093835773c03");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "30000");
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");

		activeDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		activeDriver.get("https://mobile.comeon.com");

		// Click CasinoButton
		WebDriverWait wait = new WebDriverWait(activeDriver, 500);
		WebElement casinoButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"products\"]/div[2]/h2/a/span[1]")));
		if (casinoButton.isDisplayed()) {
			casinoButton.click();
			r.setStatus("PASSED");
			System.out.println("Clicked casinoButton: " + r.getStatus());
		} else {
			r.setStatus("FAILED");
			r.setMessage("MSG: Unable to locate button - ");
			System.out.println(r.getMessage() + r.getStatus());
		}
		System.out.println("Clicked CasinoButton: " + r.getStatus());

		// Click allGames
		Thread.sleep(1000);
		WebElement allGames = activeDriver.findElement(By.xpath("//*[@id=\"products\"]/div[2]/h2/a/span[2]"));
		Thread.sleep(100);
		if (allGames.isDisplayed()) {
			allGames.click();
			r.setStatus("PASSED");
		} else {
			r.setStatus("FAILED");
			r.setMessage("MSG: Unable to locate button - ");
		}
		System.out.println("Clicked allGames: " + r.getStatus());

		// Type in the searchField
		Thread.sleep(1000);
		WebElement searchField = activeDriver
				.findElement(By.cssSelector("#app > div > header > div > div.search--min.search.u-relative > input"));
		Thread.sleep(100);
		if (searchField.isDisplayed()) {
			searchField.sendKeys("winterberries");
			r.setStatus("PASSED");
		} else {
			r.setStatus("FAILED");
			r.setMessage("MSG: Unable to locate search field - ");
		}
		System.out.println("Clicked SearchField: " + r.getStatus());

		// Click side menu
		Thread.sleep(1000);
		WebElement sideMenu = activeDriver
				.findElement(By.cssSelector("#all-games-wrapper > figure > div > div > figcaption > button"));
		Thread.sleep(100);
		if (sideMenu.isDisplayed()) {
			sideMenu.click();
			r.setStatus("PASSED");
		} else {
			r.setStatus("FAILED");
			r.setMessage("MSG: Unable to locate button - ");
		}
		System.out.println("Clicked side menu: " + r.getStatus());

		// Click button playWithoutMoney
		Thread.sleep(1000);
		WebElement withoutmoney = activeDriver
				.findElement(By.cssSelector("#app > div > nav > div > button.btn.btn--secondary.js-fun-mode"));
		Thread.sleep(100);
		if (withoutmoney.isDisplayed()) {
			withoutmoney.click();
			r.setStatus("PASSED");
		} else {
			r.setStatus("FAILED");
			r.setMessage("MSG: Unable to locate button - ");
		}
		System.out.println("Clicked button withoutMoney: " + r.getStatus());

		// ClickByImage
		CasinoFactory Casifactory = new CasinoFactory("MOBILE", (WebDriverInstance) activeDriver,
				"winterberries/start.PNG");
		ICasinoApi casino = Casifactory.getImplementation();

		r = casino.clickByImage();
		System.out.println("Clicked Image:  " + r.getStatus());
		System.out.println("Test Message: " + r.getMessage());

	}
}