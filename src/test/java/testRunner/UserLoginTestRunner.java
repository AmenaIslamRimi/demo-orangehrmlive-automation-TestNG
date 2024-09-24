package testRunner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import setUp.Setup;
import utils.Utils;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import static utils.Utils.readJSONData;

public class UserLoginTestRunner extends Setup {

    @Test(priority = 1, description = "User can login with valid creds")
    public void userLogin() throws IOException, ParseException, InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        JSONArray empArray = readJSONData();
        JSONObject empObj = (JSONObject) empArray.get(empArray.size()-1);

        String username = (String) empObj.get("userName");
        String password = (String) empObj.get("password");
        String firstname = (String) empObj.get("firstName");
        String lastname = (String) empObj.get("lastName");
        loginPage.doLogin(username,password);

        WebElement fullName_txt = driver.findElement(By.className("oxd-userdropdown-name"));
        String searchResultText = fullName_txt.getText();
        String expectedFullName = firstname + " " + lastname;
        Assert.assertTrue(searchResultText.contains(expectedFullName));
    }

    @Test(priority = 2, description = "User go to my profile to update info")
    public void gotoMyInfo() throws IOException, ParseException, InterruptedException {
        WebElement myInfo_Btn = driver.findElements(By.className("oxd-main-menu-item--name")).get(2);
        myInfo_Btn.click();

        JSONArray empArray = readJSONData();
        JSONObject empObj = (JSONObject) empArray.get(empArray.size()-1);
        String firstname = (String) empObj.get("firstName");
        String lastname = (String) empObj.get("lastName");



        WebElement myInfoTitle = driver.findElements(By.className("oxd-text")).get(10);
        String myInfoTitle_txt = myInfoTitle.getText();
        String expectedTitle = "Personal Details";
        Assert.assertTrue(myInfoTitle_txt.contains(expectedTitle));
        Thread.sleep(2000);

        Utils.scroll(driver);
        Thread.sleep(3000);
        WebElement genderRadios = driver.findElements(By.className("oxd-radio-input")).get(0);
        genderRadios.click();
        WebElement saveBtn = driver.findElements(By.cssSelector("[type='submit']")).get(0);
        saveBtn.click();


        WebElement bloodGroup = driver.findElements(By.className("oxd-select-text-input")).get(2);
        bloodGroup.click();
        WebElement selectBloodGroup = driver.findElements(By.className("oxd-select-text")).get(2);
        //selectBloodGroup.
        WebElement saveBtn2 = driver.findElements(By.cssSelector("[type='submit']")).get(1);
        saveBtn2.click();



    }
}
