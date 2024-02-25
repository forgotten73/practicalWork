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

public class CreateFormCustomers {
    public WebDriver driver;

    public CreateFormCustomers(WebDriver driver) {
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

    /**
     * Метод нажатия на кнопку таба Add Customer
     */
    @Step("Click on tab button Add Customer")
    public CreateFormCustomers clickAddCustomerTabBtn() {
        Wait.waitClickableElement(driver, addCustomerTabBtn);
        addCustomerTabBtn.click();
        return this;
    }

    /**
     * Метод заполнения полей ввода данными
     * @param firstName имя построенное из случайного кода
     * @param lastName фамилия (статическая переменная)
     * @param postCode случайный код из 10 цифр
     */
    @Step("Print to inputs customer data")
    public CreateFormCustomers fillingInputField(String firstName, String lastName, String postCode) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        postCodeInput.sendKeys(postCode);
        return this;
    }

    /**
     * Метод нажатия на кнопку Add Customer (кнопка submit в форме)
     */
    @Step("Click on submit button Add Customer")
    public CreateFormCustomers addCustomer() {
        addCustomer.click();
        return this;
    }

    /**
     * Метод подтверждения всплывающего окна alert
     */
    @Step("Alert confirm")
    public CreateFormCustomers alertConfirm() {
        Alert alert = Wait.waitAlert(driver);
        String alertText = alert.getText();
        if (alertText.contains(alertText)) {
            alert.accept();
        }
        return this;
    }

    /**
     * Метод нажатия на кнопку таба Customers
     */
    @Step("Click on tab button Customers")
    public CreateFormCustomers clickCustomersTabBtn() {
        customersTabBtn.click();
        return this;
    }

    /**
     * Метод нажатия на ссылку сортировки First Name
     */
    @Step("Click on link sort customers first name")
    public CreateFormCustomers sortedFirstNameWithClick() {
        sortFirstNameLink.click();
        return this;
    }

    /**
     * Метод получения списка имён со страницы Customers
     */
    @Step("Getting a sorted list customers after click link sorted first name")
    public List<String> getSortedFirstNameList() {
        List<WebElement> listFirstName = driver.findElements(By.xpath("//tr[@class=\"ng-scope\"]//td[1]"));
        ArrayList<String> firstNameArray = new ArrayList<>();
        for (WebElement firstName : listFirstName) {
            firstNameArray.add(firstName.getText());
        }
        return firstNameArray;
    }

    /**
     * Метод сортировки полученного списка имён в порядке убывания
     * @param resultingSortedListFirstName список имён полученный со страницы Customers
     */
    @Step("Sorted list customers")
    public boolean sortedFirstName(List<String> resultingSortedListFirstName) {
        List<String> sortedFirstName = resultingSortedListFirstName.stream()
                .sorted(Collections.reverseOrder(String::compareToIgnoreCase))
                .toList();
        if (sortedFirstName.equals(resultingSortedListFirstName)) {
            return true;
        }
        return false;
    }

    /**
     * Метод получения списка имён, отсортированных по
     * среднеарифметическому показателю длин всех имён
     * @param resultingSortedListFirstName список имён полученный со страницы Customers
     */
    @Step("Getting a string with average length among all first name")
    public List<String> getListWithAverageStrings(List<String> resultingSortedListFirstName) {
        double averageLength = resultingSortedListFirstName.stream()
                .mapToDouble(String::length)
                .average()
                .orElseThrow(NullPointerException::new);
        List<String> sorted = resultingSortedListFirstName.stream()
                .sorted(Comparator.comparingDouble(i -> Math.abs(i.length() - averageLength)))
                .collect(Collectors.toList());
        return sorted.stream()
                .filter(d -> d.length() == sorted.get(0).length())
                .collect(Collectors.toList());
    }

    /**
     * Метод удаления по клику на кнопку Delete полученых имен методом {@link #getListWithAverageStrings}
     * @param resultingSortedListFirstName список имён полученный со страницы Customers
     * @param listOfFirstNameByAverageLength список имён по
     * среднеарифметическому показателю длин всех имён
     */
    @Step("Deleting a customer closest to the average length of all customers")
    public CreateFormCustomers deleteCustomers(List<String> resultingSortedListFirstName, List<String> listOfFirstNameByAverageLength) {
        List<WebElement> deleteButtons = driver.findElements(By.xpath("//tr[@class=\"ng-scope\"]//td[5]//button"));
        Map<String, WebElement> result = IntStream.range(0, resultingSortedListFirstName.size())
                .boxed()
                .collect(Collectors.toMap(resultingSortedListFirstName::get, deleteButtons::get));

        for (String key : result.keySet()) {
            if (listOfFirstNameByAverageLength.contains(key)) {
                result.get(key).click();
            }
        }
        return this;
    }
}
