package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage {
    @FindBy(name = "username")
     WebElement userName_Txt;
    @FindBy(name = "password")
     WebElement password_Txt;
    @FindBy(css = "[type=submit]")
     WebElement login_Btn;
    @FindBy(className = "oxd-userdropdown-img")
     WebElement homePageProfile_Btn;
    @FindBy(css="[role=menuitem]")
     List<WebElement> dropDownMenu_Btn;
    @FindBy(className = "oxd-text--h6")
    public WebElement dasboard_title;
    @FindBy(className = "orangehrm-login-title")
    public WebElement loginpage_title;

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void doLogin(String username, String password)  {
        userName_Txt.sendKeys(username);
        password_Txt.sendKeys(password);
        login_Btn.click();
    }

    public void doLogout()  {
         homePageProfile_Btn.click();
         dropDownMenu_Btn.get(3).click();
    }
}
