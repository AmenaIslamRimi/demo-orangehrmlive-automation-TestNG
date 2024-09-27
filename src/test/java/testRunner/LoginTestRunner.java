package testRunner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import setUp.Setup;

public class LoginTestRunner extends Setup {
    LoginPage loginPage;



    @Test(priority = 1, description = "Admin can not Login with invalid username")
    public void doLoginIfWrongUsername(){
        loginPage = new LoginPage(driver);
        loginPage.doLogin("abc","admin123");
        WebElement alertTextForWrongLogin = driver.findElement(By.className("oxd-alert-content-text"));
        Assert.assertEquals(alertTextForWrongLogin.getText(),"Invalid credentials");
    }

    @Test(priority = 2, description = "Admin can not Login with invalid password")
    public void doLoginIfWrongPassword(){
        loginPage = new LoginPage(driver);
        loginPage.doLogin("Admin","abc");
        WebElement alertTextForWrongLogin = driver.findElement(By.className("oxd-alert-content-text"));
        Assert.assertEquals(alertTextForWrongLogin.getText(),"Invalid credentials");
    }

    @Test(priority = 3, description = "Admin can Login with valid creds")
    public void doLogin(){
        loginPage = new LoginPage(driver);
        loginPage.doLogin("Admin","admin123");
        Assert.assertTrue(loginPage.dasboard_title.isDisplayed());
    }

    //@Test(priority = 4, description = "Admin can Logout by clicking logout button")
    public void doLogout(){
        loginPage = new LoginPage(driver);
        loginPage.doLogout();
        Assert.assertTrue(loginPage.loginpage_title.isDisplayed());
    }
}
