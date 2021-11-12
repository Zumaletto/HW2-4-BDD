package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.MoneyTransferPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.*;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {
    DashboardPage dashboardPage = new DashboardPage();
    MoneyTransferPage moneyTransferPage = new MoneyTransferPage();
    CardInf card1 = getCard1();
    CardInf card2 = getCard2();

    @BeforeEach
    void setupClass() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInf();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldSendValidTransferFromCard1ToCard2() {
        int sum = 5000;

        dashboardPage.getBalanceCard(card2);
        dashboardPage.getBalanceCard(card1);
        dashboardPage.clickButton(card2);
        moneyTransferPage.transfer(sum, card1);

        assertEquals(dashboardPage.getCardBalanceMinus(dashboardPage.getBalanceCard(card1), sum),
                dashboardPage.getBalanceCard(card1));
        assertEquals(dashboardPage.getCardBalancePlus(dashboardPage.getBalanceCard(card2), sum),
                dashboardPage.getBalanceCard(card2));
    }

}
