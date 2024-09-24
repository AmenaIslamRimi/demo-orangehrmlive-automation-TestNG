package setUp;

public class EmployeeModel {

    String firstName;
    String lastName;
    String employeeId;
    String userName;
    String password;
    String confirm_password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public  EmployeeModel(String firstName, String lastName, String employeeId, String userName, String password, String confirm_password)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeId = employeeId;
        this.userName = userName;
        this.password = password;
        this.confirm_password = confirm_password;
    }

    public EmployeeModel(){

    }
}
