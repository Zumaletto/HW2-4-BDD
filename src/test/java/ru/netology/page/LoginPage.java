package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {
    private SelenideElement login = $("[data-test-id=login] input");
    private SelenideElement password = $("[data-test-id=password] input");
    private SelenideElement button = $("[data-test-id=action-login]");

    public LoginPage() {
        open("http://localhost:9999");
    }

    public VerificationPage validLogin(DataHelper.AuthInf info){
        login.setValue(info.getLogin());
        password.setValue(info.getPassword());
        button.click();
        return new VerificationPage();
    }
}
