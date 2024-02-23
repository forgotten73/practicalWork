package pages;

import helpers.Wait;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FormPage {
    public WebDriver driver;

    public FormPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//button[@ng-class=\"btnClass1\"]")
    public WebElement addCustomerTabBtn;

    @FindBy(xpath = "//input[@ng-model=\"fName\"]")
    public WebElement firstNameInput;

    @FindBy(xpath = "//input[@ng-model=\"lName\"]")
    public WebElement lastNameInput;

    @FindBy(xpath = "//input[@ng-model=\"postCd\"]")
    public WebElement postCodeInput;

    @FindBy(xpath = "//button[@type=\"submit\"]")
    public WebElement addCustomer;

    @FindBy(xpath = "//button[@ng-class=\"btnClass3\"]")
    public WebElement customersTabBtn;

    @FindBy(xpath = "//a[@ng-click=\"sortType = 'fName'; sortReverse = !sortReverse\"]")
    public WebElement sortFirstNameLink;

    @Step("Click on button for show form add customer")
    public FormPage clickAddCustomerTabBtn() {
        Wait.waitClickableElement(driver, addCustomerTabBtn);
        addCustomerTabBtn.click();
        return this;
    }

    @Step("Print to inputs customer data")
    public FormPage addInputText(String firstName, String lastName, String postCode) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        postCodeInput.sendKeys(postCode);
        return this;
    }

    @Step("Click on button add customer")
    public FormPage addCustomer() {
        addCustomer.click();
        return this;
    }

    @Step("Alert confirm")
    public FormPage alertConfirm() {
        //Alert alert =
        Wait.waitAlert(driver);
        driver.switchTo().alert().accept();
        return this;
    }

    @Step("Click on button for choice table customers")
    public FormPage clickCustomersTabBtn() {
        customersTabBtn.click();
        return this;
    }

    @Step("Click on link sort customers first name")
    public FormPage sortedFirstNameWithClick() {
        sortFirstNameLink.click();
        return this;
    }

    @Step("Getting a sorted list customers after click link sorted first name")
    public List<String> getSortedFirstNameList() {
        List<WebElement> listFirstName = driver.findElements(By.xpath("//tr[@class=\"ng-scope\"]//td[1]"));
        ArrayList<String> firstNameArray = new ArrayList<>();
        for (WebElement firstName : listFirstName) {
            firstNameArray.add(firstName.getText());
        }
        return firstNameArray;
    }

    @Step("Sorted list customers")
    public boolean sortedFirstName(List<String> sortedListFirstName) {
        List<String> sortedFirstName = sortedListFirstName.stream().sorted(Collections.reverseOrder(String::compareToIgnoreCase)).toList();
        if (sortedFirstName.equals(sortedListFirstName)) {
            return true;
        }
        return false;
    }

    @Step("Getting a string with average length among all first name")
    public List<String> getListWithAverageStrings(List<String> words) {
        double averageLength = words.stream()
                .mapToDouble(String::length)
                .average()
                .orElseThrow(NullPointerException::new);
        List<String> sorted = words.stream()
                .sorted(Comparator.comparingDouble(i -> Math.abs(i.length() - averageLength)))
                .collect(Collectors.toList());
        return sorted.stream()
                .filter(d -> d.length() == sorted.get(0).length())
                .collect(Collectors.toList());
    }

    @Step("Deleting a customer closest to the average length of all customers")
    public FormPage deleteCustomers(List<String> addList, List<String> comparedList) {
        List<WebElement> deleteButtons = driver.findElements(By.xpath("//tr[@class=\"ng-scope\"]//td[5]//button"));
        Map<String, WebElement> result = IntStream.range(0, addList.size())
                .boxed()
                .collect(Collectors.toMap(addList::get, deleteButtons::get));

        for (String key : result.keySet()) {
            if (comparedList.contains(key)) {
                result.get(key).click();
            }
        }
        return this;
    }
}
