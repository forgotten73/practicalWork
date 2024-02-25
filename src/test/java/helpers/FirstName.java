package helpers;

import io.qameta.allure.Step;

public class FirstName {

    /**
     * Метод, генерирущий случайный код из 10 цифр
     */
    @Step("Generate random post code")
    public static String generateRandomCode() {
        int randomNum;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            randomNum = (int) (Math.random() * 10);
            builder.append(randomNum);
        }
        return builder.toString();
    }

    /**
     * Метод, генерирущий случайное имя на основе метода {@link #generateRandomCode},
     * генерирующего случайный код из 10 цифр разделенного на 5 двузначных чисел
     */
    @Step("Generate random first name")
    public static String generateFirstName(String postCode) {
        char firstChar = 'a';
        String randomName = "";
        String[] arrayChars = postCode.split("");
        for (int i = 0; i < 10; i += 2) {
            randomName += (char) ((Integer.parseInt(arrayChars[i] + arrayChars[i + 1]) % 26) + firstChar);
        }
        return randomName;
    }
}
