package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement login = $("[data-test-id=login] input");
    private SelenideElement password = $("[data-test-id=password] input");
    private SelenideElement button = $("[data-test-id=action-login].button");
    private SelenideElement error = $("[data-test-id=error-notification]");

    public VerificationPage validLogin(DataHelper.AuthInf info){
        login.setValue(info.getLogin());
        password.setValue(info.getPassword());
        button.click();
        return new VerificationPage();
    }

    public void  invalidLogin(DataHelper.AuthInf info){
        login.setValue(info.getLogin());
        password.setValue(info.getPassword());
        button.click();
        error.shouldBe(visible);
    }

}
