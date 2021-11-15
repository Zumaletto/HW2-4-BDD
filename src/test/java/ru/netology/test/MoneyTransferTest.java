package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MoneyTransferTest {

    @BeforeEach
    void setupClass() {
        open("http://localhost:9999");
    }

    @Test
    @Order(1)
    void shouldSendValidTransferFromCard2toCard1() {
        val loginPage = new LoginPage();
        val authInfo = getAuthInf();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = getVerificationCodeFor();
        val dashboardPage = verificationPage.validVerify(verificationCode);
        int sumTransfer = 300;
        val balanceBeforeCard1 = dashboardPage.getBalanceCard1();
        val balanceBeforeCard2 = dashboardPage.getBalanceCard2();
        val transferPage = dashboardPage.clickButtonCard1();
        val cardInfo2 = getCard2();
        transferPage.transfer(sumTransfer, cardInfo2);
        val balanceAfterCard2 = getCardBalanceMinus(balanceBeforeCard2, sumTransfer);
        val balanceAfterCard1 = getCardBalancePlus(balanceBeforeCard1, sumTransfer);
        val balanceNewCard1 = dashboardPage.getBalanceCard1();
        val balanceNewCard2 = dashboardPage.getBalanceCard2();

        assertEquals(balanceAfterCard1, balanceNewCard1);
        assertEquals(balanceAfterCard2, balanceNewCard2);
    }

    @Test
    @Order(2)
    void shouldSendValidTransferFromCard1ToCard2() {
        val loginPage = new LoginPage();
        val authInfo = getAuthInf();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = getVerificationCodeFor();
        val dashboardPage = verificationPage.validVerify(verificationCode);
        int sumTransfer = 300;
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
    @Order(3)
    void shouldSendTransferEmpty() {
        val loginPage = new LoginPage();
        val authInfo = getAuthInf();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = getVerificationCodeFor();
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val transferPage = dashboardPage.clickButtonCard2();
        transferPage.getErrorEmptyForm();
    }

    @Test
    @Order(6)
    void shouldSendTransferOverBalanceFromCard2To1() {
        val loginPage = new LoginPage();
        val authInfo = getAuthInf();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = getVerificationCodeFor();
        val dashboardPage = verificationPage.validVerify(verificationCode);
        int sumTransfer = 11000;
        val balanceBeforeCard1 = dashboardPage.getBalanceCard1();
        val balanceBeforeCard2 = dashboardPage.getBalanceCard2();
        val transferPage = dashboardPage.clickButtonCard1();
        val cardInfo2 = getCard2();
        transferPage.transfer(sumTransfer, cardInfo2);
        getCardBalanceMinus(balanceBeforeCard2, sumTransfer);
        getCardBalancePlus(balanceBeforeCard1, sumTransfer);
        val balanceNewCard1 = dashboardPage.getBalanceCard1();
        val balanceNewCard2 = dashboardPage.getBalanceCard2();

        assertEquals(balanceBeforeCard1, balanceNewCard1);
        assertEquals(balanceBeforeCard2, balanceNewCard2);
    }

    @Test
    @Order(4)
    void shouldNoTransferClickCancel() {
        val loginPage = new LoginPage();
        val authInfo = getAuthInf();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = getVerificationCodeFor();
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val balanceBeforeCard1 = dashboardPage.getBalanceCard1();
        val balanceBeforeCard2 = dashboardPage.getBalanceCard2();
        val transferPage = dashboardPage.clickButtonCard1();
        transferPage.cancelTransfer();
        val balanceAfterCard1 = balanceBeforeCard1;
        val balanceAfterCard2 = balanceBeforeCard2;

        assertEquals(balanceAfterCard1, balanceBeforeCard1);
        assertEquals(balanceAfterCard2, balanceBeforeCard2);
    }

    @Test
    @Order(5)
    void shouldInvalidVerify() {
        val loginPage = new LoginPage();
        val authInfo = getOtherAuthInfo();
        loginPage.invalidLogin(authInfo);
        loginPage.getInvalidLogin();
    }
}
