package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MoneyTransferPage {

    private SelenideElement amount = $("[data-test-id=amount] input");
    private SelenideElement fromCard = $("[data-test-id=from] input");
    private SelenideElement buttonTransfer = $("[data-test-id=action-transfer]");
    private SelenideElement buttonCancel = $("[data-test-id=action-cancel]");
    private SelenideElement errorEmptyForm = $("[data-test-id=error-notification] ");

    public DashboardPage transfer(Integer sum, DataHelper.CardInf from) {
        amount.doubleClick().sendKeys(Keys.DELETE);
        amount.setValue(Integer.toString(sum));
        fromCard.doubleClick().sendKeys(Keys.DELETE);
        fromCard.setValue(from.getNumber());
        buttonTransfer.click();
        return new DashboardPage();
    }

    public void getErrorEmptyForm(){
        buttonTransfer.click();
        errorEmptyForm.shouldBe(visible).shouldHave(text("Ошибка! Произошла ошибка"));
    }

    public DashboardPage cancelTransfer() {
        buttonCancel.click();
        return new DashboardPage();
    }



}
