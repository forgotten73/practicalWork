package tests;

import helpers.ConfProperties;
import helpers.FirstName;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

public class PageTest extends BaseTest {

    @Test
    @DisplayName("Test method for create customer")
    @Epic("Creating customer test")
    public void creatCustomer() {
        formPage.clickAddCustomerTabBtn();
        String postCode = FirstName.generateRandomCode();
        String firstName = FirstName.generateFirstName(postCode);
        formPage.addInputText(firstName, ConfProperties.getProperty("lastName"), postCode);
        formPage.addCustomer();
        formPage.alertConfirm();
        formPage.clickCustomersTabBtn();
        List<String> list = formPage.getSortedFirstNameList();
        System.out.println(list);
        Assertions.assertTrue(list.contains(firstName), "Name incorrect");
    }

    @Test
    @DisplayName("Full Test method for page")
    @Epic("Full page test")
    public void pageTest() {
        formPage.clickAddCustomerTabBtn();
        String postCode = FirstName.generateRandomCode();
        formPage.addInputText(FirstName.generateFirstName(postCode), ConfProperties.getProperty("lastName"), postCode);
        formPage.addCustomer();
        formPage.alertConfirm();
        formPage.clickCustomersTabBtn();
        formPage.sortedFirstNameWithClick();
        List<String> list = formPage.getSortedFirstNameList();
        formPage.sortedFirstName(list);
        List<String> comparedList = formPage.getListWithAverageStrings(list);
        formPage.deleteCustomers(list, comparedList);
        List<String> afterDeleteList = formPage.getSortedFirstNameList();
        Assertions.assertFalse(afterDeleteList.containsAll(comparedList), "not all customers deleted");
    }
}
