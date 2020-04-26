package pageObjects;

import helpers.SeleniumCore;
import org.openqa.selenium.By;
import org.testng.Assert;

public class BusinessClientContactPO {

  public static final String RADIO_BUTTON_LOCATOR = ".//*[contains(label,'klient biznesowy')]//input";
  public static final String NAME_LOCATOR = "//input[@id='name']";
  public static final String EMAIL_LOCATOR = "//input[@id='email_c']";
  public static final String PHONE_LOCATOR = "//input[@id='phone']";
  public static final String SUBJECT_SELECTED_DROPDOWN_VALUE_LOCATOR = "//span[contains(@id,'subject-container')]";
  public static final String SUBJECT_DROPDOWN_ELEMENT_LOCATOR = "//li[contains(@id,'subject-result')][text()=\"%s\"]";
  public static final String TEXT_LOCATOR = "//textarea[@id='body']";
  public static final String RADIO_BUTTON_EMAIL_LOCATOR = "//input[@value='email']";
  public static final String GDPR_AGREEMENT_LOCATOR = "//input[@id='agreement_1']";
  public static final String EMAIL_SUBSCRIPTION_AGREEMENT_LOCATOR = "//input[@id='agreement_2']";
  public static final String CONTACT_AGREEMENT_LOCATOR = "//input[@id='agreement_3']";
  public static final String DISCARD_CLICK_LOCATOR = "//h1[@class='main-content__header__title']";
  SeleniumCore driver;

  public BusinessClientContactPO(SeleniumCore driver) {
    this.driver = driver;
  }


  public void findAndTickBusinessClientRadioButton() {
    driver.selectRadioButtonOrCheckbox(By.xpath(RADIO_BUTTON_LOCATOR));
  }

  public void fillClientName(String clientName) {
    driver.setText(By.xpath(NAME_LOCATOR), clientName);
    discardFocus();
    Assert.assertEquals(driver.getTextByValueAttribute(By.xpath(NAME_LOCATOR)), clientName,
        "Name is not added");
  }

  public void fillClientEmail(String clientEmail) {
    driver.setText(By.xpath(EMAIL_LOCATOR), clientEmail);
    discardFocus();
    Assert.assertEquals(driver.getTextByValueAttribute(By.xpath(EMAIL_LOCATOR)), clientEmail,
        "Email is not added");
  }

  public void fillPhoneNumber(String clientPhoneNumber) {
    driver.setText(By.xpath(PHONE_LOCATOR), clientPhoneNumber);
    discardFocus();
    Assert.assertEquals(driver.getTextByValueAttribute(By.xpath(PHONE_LOCATOR)), clientPhoneNumber,
        "Phone number is not added");
  }

  public void choseElementOnTheList(String value) {
    driver.clickElement(By.xpath(SUBJECT_SELECTED_DROPDOWN_VALUE_LOCATOR));
    driver.clickElement(By.xpath(String.format(SUBJECT_DROPDOWN_ELEMENT_LOCATOR, value)));
    driver
        .waitForDisplayedElement(By.xpath(String.format(SUBJECT_DROPDOWN_ELEMENT_LOCATOR, value)));
    Assert.assertEquals(getSelectedSubjectDropdownValue(),
        value, "Incorrect value in subject dropdown");
  }

  public void fillMessageContent(String messageContent) {
    driver.setText(By.xpath(TEXT_LOCATOR), messageContent);
  }

  public void findAndTickEmailContactRadioButton() {
    driver.selectRadioButtonOrCheckbox(By.xpath(RADIO_BUTTON_EMAIL_LOCATOR));
  }

  public void findAndTickRequiredAgreementButton() {
    driver.selectRadioButtonOrCheckbox(By.xpath(GDPR_AGREEMENT_LOCATOR));
  }

  public String getSelectedSubjectDropdownValue() {
    return driver.getText(By.xpath(SUBJECT_SELECTED_DROPDOWN_VALUE_LOCATOR));
  }

  public void findAndUnTickNotRequiredAgreements() {
    driver.unSelectCheckbox(By.xpath(EMAIL_SUBSCRIPTION_AGREEMENT_LOCATOR));
    driver.unSelectCheckbox(By.xpath(CONTACT_AGREEMENT_LOCATOR));
  }

  //method to loose focus from the previously filled field
  private void discardFocus() {
    driver.clickElement(By.xpath(DISCARD_CLICK_LOCATOR));
  }

}
