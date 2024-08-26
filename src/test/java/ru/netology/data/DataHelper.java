package ru.netology.data;

import lombok.Value;

import java.util.Random;


public class DataHelper {

    private DataHelper() {
    }

    //метод возвращает данные пользователя
    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    //метод возвращает код верификации
    public static String getVerificationCode() {
        return "12345";
    }

    //метод возвращает данные 1й карты, реализация чувствительна к пробелам в номере карты!
    public static CardInfo getFirstCardInfo() {
        return new CardInfo("5559 0000 0000 0001", "92df3f1c-a033-48e6-8390-206f6b1f56c0");
    }

    // метод, возвращающий данные второй карты, реализация чувствительна к пробелам в номере карты!
    public static CardInfo getSecondCardInfo() {
        return new CardInfo("5559 0000 0000 0002", "0f3f5c2a-249e-4c3d-8287-09f7a039391d");
    }

    // этот метод делает из немаскированного номера карты маскированный
    public static String getMaskedNumber(String cardNumber) {
        //вырезается подстрока начиная с 15 символа, можно поставить(12) - тогда с 12 символа
        //ВКЛЮЧАЯ ПРОБЕЛЫ!!!!
        return ("**** **** ****" + cardNumber.substring(15));
    }

    //2 метода для определения суммы перевода -валидную сумму перевода и инвалидную сумму перевода
    public static int generateInvalidAmount(int balance) {
        return Math.abs(balance) + new Random().nextInt(10000) + 1;
    }

    public static int generateValidAmount(int balance) {
        return new Random().nextInt(Math.abs(balance));
    }

    //дата класс - описывает карту
    @Value
    public static class CardInfo {
        String cardNumber;
        String testId;
    }

    //дата-класс - описывает обьект для аутентификации
    @Value
    public static class AuthInfo {
        String login;
        String password;
    }
}
