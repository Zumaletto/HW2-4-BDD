package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class MoneyTransferPage {

    private SelenideElement amount = $("[data-test-id=amount] input");
    private SelenideElement fromCard = $("[data-test-id=from] input");
    private SelenideElement buttonTransfer = $("[data-test-id=action-transfer]");
    private SelenideElement buttonCancel = $("[data-test-id=action-cancel]");

    public DashboardPage transfer(Integer sum, DataHelper.CardInf from) {
        amount.doubleClick().sendKeys(Keys.DELETE);
        amount.setValue(Integer.toString(sum));
        fromCard.doubleClick().sendKeys(Keys.DELETE);
        fromCard.setValue(from.getNumber());
        buttonTransfer.click();
        return new DashboardPage();
    }

    public DashboardPage cancelTransfer() {
        buttonCancel.click();
        return new DashboardPage();
    }

}
