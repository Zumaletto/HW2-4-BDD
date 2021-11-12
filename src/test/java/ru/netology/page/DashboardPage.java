package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement dashboardPage = $("[data-test-id=dashboard]");

    private SelenideElement buttonCard1 = $("[class=button__content]");
    private SelenideElement buttonCard2 = $$("[class=button__content]").get(1);
    private static SelenideElement balance1 = $("[data-test-id=\"92df3f1c-a033-48e6-8390-206f6b1f56c0\"]");
    private static SelenideElement balance2 = $("[data-test-id=\"0f3f5c2a-249e-4c3d-8287-09f7a039391d\"]");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        dashboardPage.shouldBe(visible);
    }

    public int getBalanceCard1() {
        val text = balance1.getText();
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        Integer balanceCard1 = Integer.parseInt(value);
        return balanceCard1;
    }


    public int getBalanceCard2() {
        val text = balance2.getText();
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        Integer balanceCard2 = Integer.parseInt(value);
        return balanceCard2;
    }

    public MoneyTransferPage clickButtonCard1() {
        buttonCard1.click();
        return new MoneyTransferPage();
    }

    public MoneyTransferPage clickButtonCard2() {
        buttonCard2.click();
        return new MoneyTransferPage();
    }


}
