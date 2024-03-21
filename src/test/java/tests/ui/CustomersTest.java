package tests.ui;

import helpers.ConfProperties;
import helpers.ui.FirstName;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

public class CustomersTest extends BaseTest {

    @Test
    @DisplayName("Test method for create customer")
    @Epic("Creating customer test")
    public void createCustomer() {
        formPage.clickAddCustomerTabBtn();
        String postCode = FirstName.generateRandomCode();
        String firstName = FirstName.generateFirstName(postCode);
        formPage.fillingInputField(firstName, ConfProperties.getProperty("lastName"), postCode)
                .addCustomer()
                .alertConfirm()
                .clickCustomersTabBtn();
        List<String> resultingSortedListFirstName = formPage.getSortedFirstNameList();
        Assertions.assertTrue(resultingSortedListFirstName.contains(firstName), "Name incorrect");
    }

    @Test
    @DisplayName("Sort first name method test")
    @Epic("Sort first name test")
    public void sortFirstNameTest() {
        formPage.clickAddCustomerTabBtn();
        String postCode = FirstName.generateRandomCode();
        formPage.fillingInputField(FirstName.generateFirstName(postCode), ConfProperties.getProperty("lastName"), postCode)
                .addCustomer()
                .alertConfirm()
                .clickCustomersTabBtn()
                .sortedFirstNameWithClick();
        List<String> resultingSortedListFirstName = formPage.getSortedFirstNameList();
        Assertions.assertTrue(formPage.sortedFirstName(resultingSortedListFirstName), "Sorting on the page does not work correctly");
    }

    @Test
    @DisplayName("Delete first name method test")
    @Epic("Delete first name test")
    public void deleteFirstNameTest() {
        formPage.clickAddCustomerTabBtn();
        String postCode = FirstName.generateRandomCode();
        formPage.fillingInputField(FirstName.generateFirstName(postCode), ConfProperties.getProperty("lastName"), postCode)
                .addCustomer()
                .alertConfirm()
                .clickCustomersTabBtn()
                .sortedFirstNameWithClick();
        List<String> resultingSortedListFirstName = formPage.getSortedFirstNameList();
        formPage.sortedFirstName(resultingSortedListFirstName);
        List<String> listOfFirstNameByAverageLength = formPage.getListWithAverageStrings(resultingSortedListFirstName);
        formPage.deleteCustomers(resultingSortedListFirstName, listOfFirstNameByAverageLength);
        List<String> afterDeleteList = formPage.getSortedFirstNameList();
        Assertions.assertFalse(afterDeleteList.containsAll(listOfFirstNameByAverageLength), "not all customers deleted");
    }
}
