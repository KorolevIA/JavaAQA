package Practice.Selenide.Task_6.Pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private final SelenideElement inputUserName = $("#user-name");
    private final SelenideElement inputPassword = $("#password");
    private final SelenideElement buttonLogin = $("#login-button");

    public LoginPage open() {
        Configuration.browser = "Firefox";
        Selenide.open("https://www.saucedemo.com/");
        return this;
    }

    public void loginInShop(String login, String password) {
        inputUserName.setValue(login);
        inputPassword.setValue(password);
        buttonLogin.click();
    }

}
