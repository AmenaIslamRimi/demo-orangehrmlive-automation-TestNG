package testRunner;

import com.github.javafaker.Faker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PIMPage;
import setUp.EmployeeModel;
import setUp.Setup;
import utils.Utils;

import java.io.IOException;
import java.sql.SQLOutput;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static utils.Utils.readJSONData;

public class PIMTestRunner extends Setup {

    PIMPage pimPage;
    @BeforeTest
    public void doLogin(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("Admin","admin123");
    }
    @Test(priority = 1, description = "Admin go to PIM page")
    public void gotoPIMPage() throws InterruptedException {
        pimPage = new PIMPage(driver);
        pimPage.leftMenuBar.get(1).click();
        Thread.sleep(10000);
        String messageActual = driver.findElements(By.className("oxd-text--span")).get(12).getText();
        Thread.sleep(2000);
        String messageExpected = "Records Found";
        Assert.assertTrue(messageActual.contains(messageExpected));
    }
    //@Test(priority = 2, description = "Admin create employee with missing name field")
    public void createEmployeeWithMissingValue() throws InterruptedException{
        pimPage = new PIMPage(driver);
        pimPage.addEmployee_Btn.get(2).click();
        Thread.sleep(2000);
        driver.findElement(By.className("oxd-switch-input")).click();
        EmployeeModel employee = new EmployeeModel("", "Doe", "1234", "johndoe", "P@ssword123", "P@ssword123");
        pimPage.createEmployee(employee);
        String messageActual = driver.findElements(By.className("oxd-input-field-error-message")).get(0).getText();
        String messageExpected = "Required";
        Assert.assertTrue(messageActual.contains(messageExpected));
    }
    //@Test(priority = 3, description = "Admin create employee with same username")
    public void createEmployeeWithExistedUsername() throws InterruptedException{
        pimPage = new PIMPage(driver);
        pimPage.addEmployeeNav_Btn.get(2).click();
        Thread.sleep(3000);
        driver.findElement(By.className("oxd-switch-input")).click();
        EmployeeModel employee = new EmployeeModel("john", "Doe", "1234", "Admin", "P@ssword123", "P@ssword123");
        pimPage.createEmployee(employee);
        String messageActual = driver.findElements(By.className("oxd-input-field-error-message")).get(0).getText();
        String messageExpected = "Username already exists";
        Assert.assertTrue(messageActual.contains(messageExpected));
    }

    //@Test(priority = 4, description = "Admin create employee but password and confirm password are not matched")
    public void createEmployeeWithMismatchedPassword() throws InterruptedException{
        pimPage = new PIMPage(driver);
        pimPage.addEmployeeNav_Btn.get(2).click();
        Thread.sleep(3000);
        driver.findElement(By.className("oxd-switch-input")).click();
        EmployeeModel employee = new EmployeeModel("john", "Doe", "1234", "johndoe", "P@ssword123", "Passoord123");
        pimPage.createEmployee(employee);
        String messageActual = driver.findElements(By.className("oxd-input-field-error-message")).get(0).getText();
        String messageExpected = "Passwords do not match";
        Assert.assertTrue(messageActual.contains(messageExpected));
    }
    @Test(priority = 5, description = "Admin create employee")
    public void createEmployee() throws InterruptedException, IOException, ParseException {
        pimPage = new PIMPage(driver);
        pimPage.addEmployeeNav_Btn.get(2).click();
        Thread.sleep(3000);
        String messageActual = driver.findElement(By.className("orangehrm-main-title")).getText();
        String messageExpected = "Add Employee";
        Assert.assertTrue(messageActual.contains(messageExpected));

        driver.findElement(By.className("oxd-switch-input")).click();

        Faker faker = new Faker();
        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();
        WebElement empIdElement = driver.findElements(By.className("oxd-input")).get(4);
        String employeeId = empIdElement.getAttribute("_value");
        String username = faker.name().username();
        String password = "P@ssword123";
        String confirm_password = "P@ssword123";

        EmployeeModel model = new EmployeeModel();
        model.setFirstName(firstname);
        model.setLastName(lastname);
        model.setEmployeeId(employeeId);
        model.setUserName(username);
        model.setPassword(password);
        model.setConfirm_password(confirm_password);

        pimPage.createEmployee(model);

//        System.out.println("Element found: " + empIdElement.isDisplayed());
//        System.out.println("Employee ID: " + employeeId);

        WebElement header_title_elem = driver.findElements(By.xpath("//h6[text()=\"Personal Details\"]")).get(0);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOf(header_title_elem));
        String text_title = header_title_elem.getText();
        Assert.assertTrue(text_title.contains("Personal Details"));

        Utils.saveUsers(model);

    }
    @Test(priority = 6, description = "Admin search for created employee with wrong employee id")
    public void searchCurrentUserWithWrongId() throws InterruptedException, IOException, ParseException {
        pimPage = new PIMPage(driver);
        pimPage.leftMenuBar.get(1).click();
        Thread.sleep(5000);
//        JSONArray empArray = readJSONData();
//        JSONObject empObj = (JSONObject) empArray.get(empArray.size()-1);
//        String empIdToSearch = empObj.get("employeeId").toString();
        WebElement searchByEmpId = driver.findElements(By.className("oxd-input")).get(1);
        searchByEmpId.sendKeys("000");
        //System.out.println("EmployeeId: " + empIdToSearch);
        WebElement searchBtn = driver.findElement(By.cssSelector("[type='submit']"));
        searchBtn.click();
        Thread.sleep(3000);
        WebElement searchResult = driver.findElements(By.className("oxd-text")).get(14);
        String searchResultText = searchResult.getText();
        Assert.assertTrue(searchResultText.contains("No Records Found"));
    }
    @Test(priority = 7, description = "Admin search for created employee")
    public void searchCurrentUser() throws InterruptedException, IOException, ParseException {
        pimPage = new PIMPage(driver);
        pimPage.leftMenuBar.get(1).click();
        Thread.sleep(5000);
        JSONArray empArray = readJSONData();
        JSONObject empObj = (JSONObject) empArray.get(empArray.size()-1);
        String empIdToSearch = empObj.get("employeeId").toString();
        WebElement searchByEmpId = driver.findElements(By.className("oxd-input")).get(1);
        searchByEmpId.sendKeys(empIdToSearch);
        //System.out.println("EmployeeId: " + empIdToSearch);
        WebElement searchBtn = driver.findElement(By.cssSelector("[type='submit']"));
        searchBtn.click();
        Thread.sleep(3000);
        WebElement searchResult = driver.findElements(By.className("oxd-text")).get(14);
        String searchResultText = searchResult.getText();
        Assert.assertTrue(searchResultText.contains("Record Found"));

    }

    @Test(priority = 8, description = "Admin search for created employee in Directory with wrong name")
    public void searchUserInDirectoryWithMisspelledName() throws InterruptedException, IOException, ParseException {
        pimPage = new PIMPage(driver);
        pimPage.leftMenuBar.get(8).click();
        Thread.sleep(5000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement searchByName = driver.findElement(By.cssSelector("[placeholder = 'Type for hints...']"));
        searchByName.sendKeys(" ABC");
        Thread.sleep(4000);

        //System.out.println("EmployeeId: " + empIdToSearch);
        WebElement searchBtn = driver.findElement(By.cssSelector("[type='submit']"));
        searchBtn.click();

        WebElement searchResult = driver.findElement(By.className("oxd-input-field-error-message"));
        String searchResultText = searchResult.getText();
        Assert.assertTrue(searchResultText.contains("Invalid"));

    }
    @Test(priority = 9, description = "Admin search for created employee in Directory with empty field")
    public void searchUserInDirectoryWithEmptyField() throws InterruptedException, IOException, ParseException {
        pimPage = new PIMPage(driver);
        pimPage.leftMenuBar.get(8).click();
        Thread.sleep(5000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement searchByName = driver.findElement(By.cssSelector("[placeholder = 'Type for hints...']"));
        searchByName.sendKeys(" ");
        Thread.sleep(4000);

        //System.out.println("EmployeeId: " + empIdToSearch);
        WebElement searchBtn = driver.findElement(By.cssSelector("[type='submit']"));
        searchBtn.click();

        WebElement searchResult = driver.findElement(By.className("oxd-input-field-error-message"));
        String searchResultText = searchResult.getText();
        Assert.assertTrue(searchResultText.contains("Invalid"));

    }
    @Test(priority = 10, description = "Admin search for created employee in Directory")
    public void searchUserInDirectory() throws InterruptedException, IOException, ParseException {
        pimPage = new PIMPage(driver);
        pimPage.leftMenuBar.get(8).click();
        Thread.sleep(5000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        JSONArray empArray = readJSONData();
        JSONObject empObj = (JSONObject) empArray.get(empArray.size()-1);
        String firstNameToSearch = empObj.get("firstName").toString();
        //String lastNameToSearch = empObj.get("lastName").toString();
        //String fullNameToSearch = firstNameToSearch + " " + lastNameToSearch;
        WebElement searchByName = driver.findElement(By.cssSelector("[placeholder = 'Type for hints...']"));
        searchByName.sendKeys(firstNameToSearch + " ");
        Thread.sleep(4000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[role='listbox']")));

        // Find the dropdown and select the first result
        WebElement dropdownItems = driver.findElement(By.cssSelector("[role='listbox']"));
        dropdownItems.click();  // Click on the first item in the dropdown

        //System.out.println("EmployeeId: " + empIdToSearch);
        WebElement searchBtn = driver.findElement(By.cssSelector("[type='submit']"));
        searchBtn.click();
        Thread.sleep(3000);
        WebElement searchResult = driver.findElements(By.className("oxd-text")).get(14);
        String searchResultText = searchResult.getText();
        Assert.assertTrue(searchResultText.contains("Record Found"));

    }

    @Test(priority = 9)
    public void doLogout(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogout();
        Assert.assertTrue(loginPage.loginpage_title.isDisplayed());
    }

    //@Test(priority = 5, description = "Admin create employee but password is weak")
//    public void createEmployeeWithWeakPassword() throws InterruptedException {
//        pimPage = new PIMPage(driver);
//        pimPage.addEmployeeNav_Btn.get(2).click();
//        Thread.sleep(3000);
//        driver.findElement(By.className("oxd-switch-input")).click();
//        pimPage.createEmployee("test62", "user78", "0123", "test62user78", "pass123", "pass123");
//        String messageActual = driver.findElements(By.className("oxd-input-field-error-message")).get(0).getText();
//        Thread.sleep(2000);
//        String messageExpected = "Should have at least 7 characters";
//        Assert.assertTrue(messageActual.contains(messageExpected));
//    }



}
