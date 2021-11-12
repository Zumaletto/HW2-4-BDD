package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement dashboardPage = $(byText("Ваши карты"));
    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private ElementsCollection buttons = $$("[data-test-id=action-deposit]");


    public DashboardPage() {
        dashboardPage.shouldBe(visible);
    }

    public int getBalanceCard(DataHelper.CardInf card) {
        val text = cards.findBy(text(card.getNumber().substring(12, 16))).getText();
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        Integer balance = Integer.parseInt(value);
        return balance;
    }

    public MoneyTransferPage clickButton(DataHelper.CardInf card) {
        buttons.findBy(text("Пополнить")).click();
        return new MoneyTransferPage();
    }

    public int getCardBalancePlus(int balance, int sumTransfer){
        int cardBalancePlus = balance + sumTransfer;
        return cardBalancePlus;
    }

    public int getCardBalanceMinus(int balance, int sumTransfer){
        int cardBalanceMinus = balance - sumTransfer;
        if (cardBalanceMinus < 0){
            return balance;
        }
        return cardBalanceMinus;
    }

}
