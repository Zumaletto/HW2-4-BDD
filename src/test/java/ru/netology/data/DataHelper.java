package ru.netology.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInf {
        private String login;
        private String password;
    }

    public static AuthInf getAuthInf() {
        return new AuthInf("vasya", "qwerty123");
    }

    public static AuthInf getOtherAuthInfo() {
        return new AuthInf("oleg", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor() {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardInf {
        private String number;
        private String balance;
    }

    public static CardInf getCard1() {

        return new CardInf( "5559 0000 0000 0001", "10 000");
    }

    public static CardInf getCard2() {

        return new CardInf("5559 0000 0000 0002", "10 000");
    }

    public static int getCardBalancePlus(int balance, int sumTransfer){
        int cardBalancePlus = balance + sumTransfer;
        return cardBalancePlus;
    }

    public static int getCardBalanceMinus(int balance, int sumTransfer){
        int cardBalanceMinus = balance - sumTransfer;
        if (cardBalanceMinus < 0){
            return balance;
        }
        return cardBalanceMinus;
    }

}
