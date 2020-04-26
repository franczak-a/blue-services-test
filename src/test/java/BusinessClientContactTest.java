import org.testng.annotations.Test;
import pageObjects.BusinessClientContactPO;

public class BusinessClientContactTest extends SeleniumBase {

  public static final String CLIENT_NAME = "BlueServices Test";
  public static final String CLIENT_EMAIL = "bs@blueservices.pl";
  public static final String CLIENT_PHONE_NUMBER = "+48 123 123 123";
  public static final String MESSAGE_CONTENT = "automat test Blueservices";
  public static final String SUBJECT_DROPDOWN_VALUE = "Przelewy natychmiastowe";


  @Test
  public void FillOutForm() {
    BusinessClientContactPO businessClientContactPO = new BusinessClientContactPO(driver);
    businessClientContactPO.findAndTickBusinessClientRadioButton();
    businessClientContactPO.fillClientName(CLIENT_NAME);
    businessClientContactPO.fillClientEmail(CLIENT_EMAIL);
    businessClientContactPO.fillPhoneNumber(CLIENT_PHONE_NUMBER);
    businessClientContactPO.choseElementOnTheList(SUBJECT_DROPDOWN_VALUE);
    businessClientContactPO.fillMessageContent(MESSAGE_CONTENT);
    businessClientContactPO.findAndTickEmailContactRadioButton();
    businessClientContactPO.findAndTickRequiredAgreementButton();
  }
}