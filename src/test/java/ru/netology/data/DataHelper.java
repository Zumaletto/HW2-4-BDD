package ru.netology.data;

import lombok.Value;
import ru.netology.page.DashboardPage;
import ru.netology.page.MoneyTransferPage;

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

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInf authInf) {

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

}
