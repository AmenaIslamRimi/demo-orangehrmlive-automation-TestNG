package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import setUp.EmployeeModel;

import java.time.Duration;
import java.util.List;

public class PIMPage {

    @FindBy(className = "oxd-main-menu-item--name")
    public List<WebElement> leftMenuBar;
    @FindBy(className = "oxd-button")
    public List<WebElement> addEmployee_Btn;

    @FindBy(className = "oxd-topbar-body-nav-tab-item")
    public List<WebElement> addEmployeeNav_Btn;

    @FindBy(className = "oxd-input")
    public List <WebElement> createEmpTextField;

    public PIMPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void createEmployee(EmployeeModel model) {
        createEmpTextField.get(1).sendKeys(model.getFirstName());
        createEmpTextField.get(3).sendKeys(model.getLastName());
        createEmpTextField.get(4).getAttribute("_value");
        createEmpTextField.get(5).sendKeys(model.getUserName());
        createEmpTextField.get(6).sendKeys(model.getPassword());
        createEmpTextField.get(7).sendKeys(model.getConfirm_password());
        addEmployee_Btn.get(1).click();

    }


}
