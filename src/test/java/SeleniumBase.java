import helpers.SeleniumCore;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class SeleniumBase {

  SeleniumCore driver;
  public static final String URL = "https://bluemedia.pl/kontakt";
  public static final String TITLE = "Kontakt - Bluemedia";

  public SeleniumBase() {
    driver = new SeleniumCore();
  }

  @BeforeMethod
  public void BeforeTest() {
    driver.navigate(URL);
    driver.getDriver().getTitle();
    Assert.assertEquals(driver.getDriver().getTitle(), TITLE, "Incorrect page title");
  }

  @AfterMethod
  public void AfterTest() {
    driver.getDriver().quit();
  }
}
