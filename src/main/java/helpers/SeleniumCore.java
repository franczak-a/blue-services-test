package helpers;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumCore {

  WebDriver driver;
  WebDriverWait wait;

  public SeleniumCore() {
    System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    wait = new WebDriverWait(driver, 5, 200);
  }

  public void navigate(String url) {
    driver.get(url);
    waitForPageLoad();
  }

  private void waitForPageLoad() {
    wait.until((ExpectedCondition<Boolean>) d -> Objects
        .equals(executeScript("return document.readyState"), "complete"));
  }

  private Object executeScript(String script, Object... args) {
    return ((JavascriptExecutor) driver).executeScript(script, args);
  }

  public WebDriver getDriver() {
    return driver;
  }

  private WebDriverWait getWait() {
    return wait;
  }

  public void clickElement(By by) {
    waitForElementToBeVisible(by);
    driver.findElement(by).click();

  }

  private void waitForElementToBeVisible(By by) {
    getWait().until(ExpectedConditions.numberOfElementsToBeMoreThan(by, 0));
  }

  public void waitForDisplayedElement(By by) {
    getWait().until(ExpectedConditions.numberOfElementsToBeLessThan(by, 1));
  }

  public void setText(By by, String text) {
    waitForElementToBeVisible(by);
    WebElement element = driver.findElement(by);
    element.clear();
    element.sendKeys(text);
  }

  public String getTextByValueAttribute(By by) {
    waitForElementToBeVisible(by);
    return driver.findElement(by).getAttribute("value");
  }

  public String getText(By by) {
    waitForElementToBeVisible(by);
    return driver.findElement(by).getText();
  }

  public void selectRadioButtonOrCheckbox(By by) {
    if (!driver.findElement(by).isSelected()) {
      clickElement(by);
    }
  }

  public void unSelectCheckbox(By by) {
    if (driver.findElement(by).isSelected()) {
      clickElement(by);
    }
  }
}
