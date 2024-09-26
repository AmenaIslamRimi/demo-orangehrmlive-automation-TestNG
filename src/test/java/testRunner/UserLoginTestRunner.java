package testRunner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import setUp.Setup;
import utils.Utils;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import static utils.Utils.readJSONData;

public class UserLoginTestRunner extends Setup {

    @Test(priority = 1, description = "New user can login with valid creds")
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

    @Test(priority = 2, description = "New user go to my profile to update info")
    public void gotoMyInfo() throws IOException, ParseException, InterruptedException {
        WebElement myInfo_Btn = driver.findElements(By.className("oxd-main-menu-item--name")).get(2);
        myInfo_Btn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

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
        WebElement maleRadio = driver.findElement(By.xpath("//label[text()=\"Male\"]"));
        WebElement femaleRadio = driver.findElement(By.xpath("//label[text()=\"Female\"]"));
        Random random = new Random();
        int randomGender = random.nextInt(2);  // Generates either 0 or 1

        // Click the corresponding radio button based on the random number
        if (randomGender == 0) {
            maleRadio.click();
            //System.out.println("Male radio button selected.");
        } else {
            femaleRadio.click();
            //System.out.println("Female radio button selected.");
        }
        WebElement saveBtn = driver.findElements(By.cssSelector("[type='submit']")).get(0);
        saveBtn.click();
        Thread.sleep(2000);


        WebElement bloodGroup = driver.findElements(By.className("oxd-select-text-input")).get(2);
        bloodGroup.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[role='listbox']")));
        WebElement selectBloodGroup = driver.findElement(By.cssSelector("[role='listbox']"));
        selectBloodGroup.getAttribute("O+");
        Thread.sleep(2000);

        WebElement saveBtn2 = driver.findElements(By.cssSelector("[type='submit']")).get(1);
        saveBtn2.click();

    }

    @Test (priority = 3, description = "New user can logout")
    public void doLogout(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogout();
        Assert.assertTrue(loginPage.loginpage_title.isDisplayed());
    }
}
