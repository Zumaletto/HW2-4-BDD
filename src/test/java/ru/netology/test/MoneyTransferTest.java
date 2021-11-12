package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.*;

public class MoneyTransferTest {

    @BeforeEach
    void setupClass() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSendValidTransferFromCard1ToCard2() {
        val loginPage = new LoginPage();
        val authInfo = getAuthInf();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = getVerificationCodeFor();
        verificationPage.validVerify(verificationCode);
        val dashboardPage = new DashboardPage();
        int sumTransfer = 3000;
        val balanceBeforeCard1 = dashboardPage.getBalanceCard1();
        val balanceBeforeCard2 = dashboardPage.getBalanceCard2();
        val transferPage = dashboardPage.clickButtonCard2();
        val cardInfo1 = getCard1();
        transferPage.transfer(sumTransfer, cardInfo1);
        val balanceAfterCard1 = getCardBalanceMinus(balanceBeforeCard1, sumTransfer);
        val balanceAfterCard2 = getCardBalancePlus(balanceBeforeCard2, sumTransfer);
        val balanceNewCard1 = dashboardPage.getBalanceCard1();
        val balanceNewCard2 = dashboardPage.getBalanceCard2();

        assertEquals(balanceAfterCard1, balanceNewCard1);
        assertEquals(balanceAfterCard2, balanceNewCard2);
    }

    @Test
    void shouldSendValidTransferFromCard2toCard1() {
        val loginPage = new LoginPage();
        val authInfo = getAuthInf();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = getVerificationCodeFor();
        verificationPage.validVerify(verificationCode);
        val dashboardPage = new DashboardPage();
        int sumTransfer = 4000;
        val balanceBeforeCard1 = dashboardPage.getBalanceCard1();
        val balanceBeforeCard2 = dashboardPage.getBalanceCard2();
        val transferPage = dashboardPage.clickButtonCard1();
        val cardInfo2 = getCard2();
        transferPage.transfer(sumTransfer, cardInfo2);
        val balanceAfterCard1 = getCardBalanceMinus(balanceBeforeCard1, sumTransfer);
        val balanceAfterCard2 = getCardBalancePlus(balanceBeforeCard2, sumTransfer);
        val balanceNewCard1 = dashboardPage.getBalanceCard1();
        val balanceNewCard2 = dashboardPage.getBalanceCard2();

        assertEquals(balanceAfterCard1, balanceNewCard1);
        assertEquals(balanceAfterCard2, balanceNewCard2);
    }

    @Test
    void shouldSendTransferEmpty() {
        val loginPage = new LoginPage();
        val authInfo = getAuthInf();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = getVerificationCodeFor();
        verificationPage.validVerify(verificationCode);
        val dashboardPage = new DashboardPage();
        $$("[class=button__content]").get(1).click();
        $("[data-test-id=action-transfer]").click();
        $("[data-test-id=error-notification]").shouldBe(visible, Duration.ofSeconds(30));
        $("[data-test-id=error-notification] .notification__title")
                .shouldHave(exactText("Ошибка"));
        $("[data-test-id=error-notification] .notification__content")
                .shouldHave(exactText("Ошибка! Произошла ошибка"));
    }

    @Test
    void shouldSendTransferOverBalanceFromCard2To1() {
        val loginPage = new LoginPage();
        val authInfo = getAuthInf();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = getVerificationCodeFor();
        verificationPage.validVerify(verificationCode);
        val dashboardPage = new DashboardPage();
        int sumTransfer = 40000;
        val balanceBeforeCard1 = dashboardPage.getBalanceCard1();
        val balanceBeforeCard2 = dashboardPage.getBalanceCard2();
        val transferPage = dashboardPage.clickButtonCard1();
        val cardInfo2 = getCard2();
        transferPage.transfer(sumTransfer, cardInfo2);
        val balanceAfterCard1 = getCardBalanceMinus(balanceBeforeCard1, sumTransfer);
        val balanceAfterCard2 = getCardBalancePlus(balanceBeforeCard2, sumTransfer);

        assertEquals(balanceAfterCard1, balanceBeforeCard1);
        assertEquals(balanceAfterCard2, balanceBeforeCard2);
    }

    @Test
    void shouldNoTransferClickCancel() {
        val loginPage = new LoginPage();
        val authInfo = getAuthInf();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = getVerificationCodeFor();
        verificationPage.validVerify(verificationCode);
        val dashboardPage = new DashboardPage();
        val balanceBeforeCard1 = dashboardPage.getBalanceCard1();
        val balanceBeforeCard2 = dashboardPage.getBalanceCard2();
        val transferPage = dashboardPage.clickButtonCard1();
        $("[data-test-id=action-transfer]").click();
        val balanceAfterCard1 = balanceBeforeCard1;
        val balanceAfterCard2 = balanceBeforeCard2;

        assertEquals(balanceAfterCard1, balanceBeforeCard1);
        assertEquals(balanceAfterCard2, balanceBeforeCard2);
    }

    @Test
    void shouldInvalidVerify() {
        val loginPage = new LoginPage();
        val authInfo = getOtherAuthInfo();
        loginPage.invalidLogin(authInfo);
        $("[data-test-id=error-notification] .notification__title")
                .shouldHave(exactText("Ошибка"));
        $("[data-test-id=error-notification] .notification__content")
                .shouldHave(exactText("Ошибка! Неверно указан логин или пароль"));
    }
}
